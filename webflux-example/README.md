# Reactive REST API with Spring WebFlux

The server will start at http://localhost:8080.

## Prerequisites

	* JAVA 8 should be installed
	* Postgres should be up and running at : localhost:5432
	
	To start Postgresql:
	docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
	docker ps
	docker cp schema.sql f9baabe899cb:/schema.sql
	docker exec -it postgres /bin/bash
	psql -d test -a -f /schema.sql
	
## Build
```
mvn clean install
```

## Unit Testing
```
mvn test
```

## Run
```
mvn exec:java
```

## Integration Testing
	Bash
```
curl -i localhost:8080/users/1
curl -i localhost:8080/users
curl -i -H "Content-Type: application/json" -d '{"name":"Jack Lee", "age":30, "salary":100000.0}' -X POST localhost:8080/users 

```