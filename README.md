# kotlin-http-patch

A minimal [Kotlin](https://kotlinlang.org) and [Spring Boot](https://spring.io/projects/spring-boot) application for
testing `HTTP PATCH`. 

Since [the standard JDK HTTP library does not support HTTP PATCH](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html#patchForObject-java.lang.String-java.lang.Object-java.lang.Class-java.util.Map-)
by default, Spring must be able to locate a `ClientHttpRequestFactory` that does support `HTTP PATCH`.

Spring autoconfigures it's `RestTemplateBuilder`, and if either `HttpComponentsClientHttpRequestFactory`
or `OkHttp3ClientHttpRequestFactory` are found, they will be used, and they do provided the necessary support
for `HTTP PATCH`.

## Usage

Run the downstream backend with:

```sh
./scripts/json-server start
```

Call the application, listening on port 9000, with:

```sh
# Fetch all books
curl -s -X GET  http://localhost:9000/books 

# Update a book
curl -s  http://localhost:9000/books/3 \
-H "Content-Type: application/json" \
-X PATCH --data '{"author": "New Author", "title": "New Title"}'
```

## Requirements

1. A working [Docker]() environment.

