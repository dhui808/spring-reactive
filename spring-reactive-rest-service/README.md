## Spring Web Stacks
   <img src="https://spring.io/img/extra/reactive-5.svg" width="600" height="400">

## Spring Reactive RESTful Web Service
Build a reactive RESTful web service with Spring WebFlux and a WebClient consumer of that service.

## Build
mvn clean install

## Run
mvn exec:java

## Test with http
http :8080/hello

## Unit test
mvn test

## Note
Intentionally introduced a delay of 3s for the /hello service, to test the timeout and retry behavior of the client like Spring Cloud Gateway.
