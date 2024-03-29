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
# Assuming project root is the current directory.
scripts/json-server start data/books.json  
```

Call the application, listening on port 9000, with:

```sh
# Fetch all books
curl -s -X GET  http://localhost:9000/books 

# Update a book
curl -s  http://localhost:9000/books/3 \
-H "Content-Type: application/json" \
--data '{"author": "New Author", "title": "New Title"}'
```

Remove `implementation("com.squareup.okhttp3:okhttp:4.10.0")` from the `build.gradle.kts` to get this application to fail
on the update call.

## Requirements

1. A working [Docker](https://www.docker.com/) environment.

