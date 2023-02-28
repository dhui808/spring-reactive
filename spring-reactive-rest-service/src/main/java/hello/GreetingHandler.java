package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

	@Value("${timeout:3000}")
	private int timeout;
	
	public Mono<ServerResponse> hello(ServerRequest request) {
		try {
			System.out.println("Thread Name="+ Thread.currentThread().getName());
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		throw new RuntimeException("Test Exception.");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
	}
}
