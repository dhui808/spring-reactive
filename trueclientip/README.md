### HttpServletRequest
	It is wrong to use HttpServletRequest as a instance variable of a singleton
	class. However, it is a different story in Spring.
	
	@Autowired HttpServletRequest and passing HttpServletRequest as a parameter 
	are the same things.

	Before passing HttpServletRequest to invocation method responding to 	
	@RequestMapping function, Spring stores the HttpServletRequest into a 	
	ThreadLocal type variable.

	That ThreadLocal variable is a thread-safe map that keeps 	
	HttpServletRequest in the current thread context. The @Autowired 	
	HttpServletRequest proxy bean gets the correct request from that 	
	ThreadLocal variable.
	
	Spring exposes the current HttpServletRequest object (as well as the 
	current HttpSession object) through a wrapper object of type 
	ServletRequestAttributes. This wrapper object is bound to ThreadLocal and 
	is obtained by calling the static method 
	RequestContextHolder.currentRequestAttributes().

	ServletRequestAttributes provides the method getRequest() to get the 
	current request, getSession() to get the current session and other methods 
	to get the attributes stored in both the scopes.

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
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 30 9 * * *" --firstname=One --count=100
	
	Start another Scheduled task
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 30 9 * * *" --firstname=Two --server.port=8082 --clientIp=2.2.2.2 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 30 9 * * *" --firstname=Three --server.port=8083 --clientIp=3.3.3.3 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 30 9 * * *" --firstname=Four --server.port=8084 --clientIp=4.4.4.4 --count=100
	
	java -jar trueclientip-scheduled-webclient-0.0.1.jar --cron.schedule="0 30 9 * * *" --firstname=Five --server.port=8085 --clientIp=5.5.5.5 --count=100
	
	
	

