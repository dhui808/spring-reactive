# Reactive REST API with Spring WebFlux

The server will start at http://localhost:8080.

### Docker Postres Image Usage
	--name postgres					Container name is postgres
	-e POSTGRES_PASSWORD=postgres	Environment variable POSTGRES_PASSWORD
	-p 5432:5432					Port
	-d, --detach					Run container in background and print container ID
	postgres						Docker Postgres name is postgres

## Prerequisites

	* JAVA 8 should be installed
	* Postgres should be up and running at : localhost:5432
	
	To start Postgresql:
	docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
	docker ps
	docker cp src/main/resources/schema.sql f9baabe899cb:/schema.sql
	docker exec -it postgres /bin/bash
	createdb -U postgres test
	psql -U postgres -d test -a -f /schema.sql
	
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