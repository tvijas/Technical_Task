# GitHub Repositories API

This is a Quarkus-based REST API that allows users to retrieve a list of GitHub repositories for a specified user, excluding forks. For each repository, it provides the repository name, owner login, and details about its branches (branch name and last commit SHA).

## Features
- Retrieve a list of non-fork repositories for a GitHub user.
- Include branch information (name and last commit SHA) for each repository.
- Return a 404 error with a structured response if the user does not exist.

# How to Run

## Requirements ( In my case everything works with such setup )
- GraalVM CE 21.0.2
- maven 3.9.6

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```
