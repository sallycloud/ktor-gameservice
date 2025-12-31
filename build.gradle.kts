import com.github.jk1.license.render.JsonReportRenderer
import com.github.jk1.license.render.ReportRenderer
import com.github.jk1.license.render.TextReportRenderer

val gameserviceVersion = libs.versions.ktor.gameservice.get()
val dockerImageName = "sallylockhart/ktor-gameservice:$gameserviceVersion"

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.license.report)
}

repositories {
    mavenCentral()
}

group = "de.kr"
version = libs.versions.ktor.gameservice.get()

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.cors)
    implementation(libs.logback.classic)
    implementation(libs.kotlin.logging)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.resources)

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}

licenseReport {
    configurations = arrayOf("runtimeClasspath")
    renderers = arrayOf<ReportRenderer>(
        JsonReportRenderer("licenses.json"),
        TextReportRenderer("licenses.txt")
    )
    outputDir = layout.buildDirectory.dir("reports/dependency-license").get().asFile.absolutePath
}

tasks.register<Copy>("copyLicenseJsonToResources") {
    dependsOn("generateLicenseReport")
    from("build/reports/dependency-license/licenses.json")
    into("$buildDir/resources/main/licenses")
}

tasks.named<ProcessResources>("processResources") {
    dependsOn("copyLicenseJsonToResources")
    filesMatching("application.yaml") {
        expand("version" to project.version)
    }
}

tasks.named<JavaExec>("run") {
    dependsOn("processResources")
}

tasks.register<Exec>("buildDockerImage") {
    group = "docker"
    description = "Builds Docker image with Ktor Fat JAR and License Report"
    dependsOn("shadowJar")
    commandLine(
        "docker", "build",
        "-t", dockerImageName,
        "."
    )
}

tasks.named<ProcessResources>("processResources") {
    filesMatching("openapi/documentation.yaml") {
        filter { line -> line.replace("@VERSION@", project.version.toString()) }
    }
}

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

tasks.register<Exec>("pushDockerImage") {
    group = "docker"
    description = "Pushes the Docker image"
    dependsOn("buildDockerImage")
    commandLine(
        "docker", "push", dockerImageName
    )
}

