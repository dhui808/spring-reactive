It is wrong to use HttpServletRequest as a instance variable of a singleton class. These this application tries to show the impact of
such an approach.

### Run
	Start server
	
	curl localhost:8081
	curl localhost:8081?volume=1
	curl 'localhost:8081?volume=1&ip=5.6.7.8'
	curl 'localhost:8081?volume=100&ip=1.1.1.1&name=Ann'
	curl 'localhost:8081?volume=100&ip=2.2.2.2&name=Joe'

### Run Spring RestTemplate client

### Run Spring WebClient

