# gameservice

This is merely an example web service created as a practical exercise during a course.
It is a REST API that can provide basic information on board games. Right now only 3 games
a available. It is just a finger exercise, not a real service that provides actual benefit.

## Building and running locally

After cloning the repo, you can build with:

```bash
./gradlew clean build
```

Run the application with:

```bash
./gradlew run
```

## Using the service

After `.gradlew run`, the service should be available under `http://localhost:8080`.

Swagger-UI can be found under path GET `/swagger`.

A list of all games is available under path GET `/games`.

You can filter for a specific game using GET `/games?name=Chess`.

## Building, pulling, running a Docker image

After building the project locally with `./gradlew clean build`, you can build an image:

```bash
docker build -t sallylockhart/ktor-gameservice:latest .
```

But you don't have to build the image yourself, since it is available 
on [Docker Hub](https://hub.docker.com/repository/docker/sallylockhart/ktor-gameservice/general).
So you can simply pull with:

```bash
docker pull sallylockhart/ktor-gameservice:latest
```

You can then run the image with:

```bash
docker run -p 8080:8080 sallylockhart/ktor-gameservice:latest
```

## Running instance on Google Cloud Runner

A current version of the image is automatically deployed to [Google Cloud](https://cloud.google.com/).
The running instance can be accessed [here](https://ktor-gameservice-592911050664.europe-west1.run.app/).

Since this is just an exercise for a course, it is likely that I will stop the service 
after finishing the course.