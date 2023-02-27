## Spring RestTemplat vs WebClient

Performance comparison between RestTemplat and WebClient

### Issue
	Cannot inject dependencies for WebController during integration testing.

### Test
	curl localhost:8080/chat-blocking -v
	curl localhost:8080/chat-non-blocking -v
	