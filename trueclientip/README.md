### HttpServletRequest
	It is wrong to use HttpServletRequest as a instance variable of a singleton
	class. These this application tries to show the impact of such an approach.

### Run
	Start server
	
	curl localhost:8081
	curl localhost:8081?volume=1
	curl 'localhost:8081?volume=1&ip=5.6.7.8'
	curl 'localhost:8081?volume=100&ip=1.1.1.1&name=Ann'
	curl 'localhost:8081?volume=100&ip=2.2.2.2&name=Joe'

### Option 1 - Run Spring RestTemplate client

### Option 2 - Run Spring WebClient

### Option 3 - Run Spring Scheduled Task with WebClient
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 10 18 * * *" --firstname=One --count=100
	
	Start another Scheduled task
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 10 18 * * *" --firstname=Two --server.port=8082 --clientIp=2.2.2.2 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 10 18 * * *" --firstname=Three --server.port=8083 --clientIp=3.3.3.3 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 10 18 * * *" --firstname=Four --server.port=8084 --clientIp=4.4.4.4 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 10 18 * * *" --firstname=Five --server.port=8085 --clientIp=5.5.5.5 --count=100
	
	
	

