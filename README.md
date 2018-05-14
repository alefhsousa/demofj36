A simple example of microservices explain in class the course `FJ36` in [Caelum](https://www.caelum.com.br/curso-java-ee-soa-web-services-mensageria).

There are two services:
* RESTful service - exposes a REST API for create a student.
It saves the student  in MongoDB and posts a message to RabbitMQ.

* Client application - consume the messages was posted in RabbitMQ

The services are written in Scala and use the following technologies.

* Spring Boot
* Spring Cloud
* MongoDB
* RabbitMQ


# Building and running the microservices

This project uses with [Docker Compose](https://docs.docker.com/compose/) to run the services as well as RabbitMQ and MongoDB.


## The quick way

For more easily setup this projects use [Makefile](http://www.cs.colby.edu/maxwell/courses/tutorials/maketutor/) with the basics tasks for running the project.
The whole idea to use `make` aims into the premise of providing almost-zero-setup requirement to run
day-to-day task when developing and deploying an application.

Following the tasks: 

### Tasks

- `make build`: Build jars of all projects and generate containers images
- `make up`: Setup all dependencies and running containers
- `make down`: Delete all dependencies of project and containers
- `make docker/image/client`: Generate a image of `client-demo` with specific parameters `name` and `version`.
- `make docker/image/demo`: Generate a image of `demofj36` with specific parameters `name` and `version`.