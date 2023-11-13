# XP Card Application

## TDD e il mito del 100% code coverage @ IAD2023

> Every day is a learning day.

## Project Description
`XP Card Application` is a web application with a back-end in Spring Boot written in Java with a front-end written in React. Its goal is to keep track of the balance and movements of XP points on a virtual card, known as an `XP card`.

People earn XP points respecting the practices:

* The Planning Game
* Small Releases
* Metaphor
* Simple Design
* TDD
* Refactoring
* Pair Programming
* Collective Ownership
* Continuous Integration
* 40-hour week
* On-site Customer
* Coding Standard

People loose XP points violating the practices:
* Working overtime
* Over engineering
* Working alone the most of time
* Working on branch lasting month and rarely integrated in main
* etc. etc.


## How to Install and Run the Project

### Install Requirements
The application needs:

- Java 17 or higher as a run time for the back-end
- Maven 3.9.5 or higher as build tool
- node.js v20.5.1 or higher for the front-end
- MongoDB or alternatively
- Docker environment for your operating system and architecture

In the code base, there are some acceptance tests that need a DB instance running, so you have to install libraries first before running tests:
```bash
$ mvn install -Dmaven.test.skip
```

### Run the Project
It is possible to run MongoDB in a lot of different ways. Inside this repository is a Docker Compose file suitable to run MongoDB and Mongo Express web clients.

```bash
$ docker compose up
```
The MongoDB web client is reachable at the following URL [http://localhost:8081/](http://localhost:8081/).

After all dependencies are installed, you run all tests and the entire application.
For tests:
```bash
$ mvn test
```
For application:
```bash
$ mvn spring-boot:run
```

## Run tests for coverage
Code coverage is a software metric used to measure how many lines of our code are executed during automated tests.

_JaCoCo_ is a code coverage report generator for Java projects already configured in `pom.xml`.

You can use `jacoco:report` goal in order to generate readable code coverage reports in several formats, like HTML, CSV, and XML.
```bash
$ mvn jacoco:report
```
Now you can take a look at the `target/site/jacoco/index.html` page to see what the generated report looks like.

## Credits
Thanks to the guys at XPUG Bergamo for their precious feedback on these project ideas.
