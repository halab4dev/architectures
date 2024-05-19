# Onion Architecture
This is an example of onion architecture, implemented with Spring Boot.

## Packages
- `common`: constants and utilities classes that can be used in anywhere or for multi project
- `domain`: contain business logic of domains
- `application`: business logic of application
- `infrastructure`: concrete implements, which depend on framework, external component (database, 3rd service ...)

## How to run
### Prepare a database
For this example, start PostgreSQL with docker

- Create a docker network
```shell
docker network create architectures
```
- Run postgres container
```shell
docker run -d \
--name architectures-postgres \
--network=architectures \
-p 5432:5432 \
-e POSTGRES_USER={POSTGRES_USER} \
-e POSTGRES_PASSWORD={POSTGRES_PASSWORD} \
-v $(pwd)/data/postgres:/opt \
-d postgres:15.5
```

### Set environment variables
Set environment variables listed in `[.env.template](.env.template)` file

### Start the application
Start the application, you can test with API in [api](src/main/java/com/github/halab4dev/infrastructure/api)

## References
- [Onion Architecture - Let’s slice it like a Pro](https://medium.com/expedia-group-tech/onion-architecture-deed8a554423)
