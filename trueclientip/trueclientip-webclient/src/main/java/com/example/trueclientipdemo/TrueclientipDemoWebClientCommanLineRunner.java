package com.example.trueclientipdemo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@Slf4j
@Data
public class TrueclientipDemoWebClientCommanLineRunner implements CommandLineRunner {

	@Value("${host}")
	String host;
	
	@Value("${count:10}")
	int count;

	@Value("${clientIp:1.1.1.1}")
	String clientIp;
	
	@Value("${firstname:Joe}")
	String firstname;

	@Value("${clientIp2:2.2.2.2}")
	String clientIp2;
	
	@Value("${firstname2:Mary}")
	String firstname2;
	
	WebClient webClient;
	
	public TrueclientipDemoWebClientCommanLineRunner(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
	}
	
	@Override
	public void run(String... args) throws Exception {
		makeCall(count, clientIp, firstname, clientIp2, firstname2);
	}

	@GetMapping("/")
	public String callServer(@RequestParam("volume") Optional<Integer> volume,
			@RequestParam("ip") Optional<String> ip,
			@RequestParam("name") Optional<String> name) {
		
		int size = count;
	    if ( volume.isPresent()){
	    	size = volume.get();
	    	if (0 == size) {
	    		size = count;
	    	}
	    } 
	    
	    String ipAddr = clientIp;
	    if (ip.isPresent()) {
	    	ipAddr = ip.get();
	    }
	    
	    String fn = firstname;
	    if (name.isPresent()) {
	    	fn = name.get();
	    }
	    
	    String s = makeCall(size, ipAddr, fn, ipAddr, fn);
	    
	    return s;
	}

	private String makeCall(int size, String ipAddr, String name,
			String ipAddr2, String name2) {
		
		log.info("Making "+ size + " calls...");
		log.info("ipAddr=" + ipAddr + " name=" + name + " ipAddr2=" + ipAddr2 + " name2=" + name2);
		
        createFlux(size, ipAddr, name, ipAddr2, name2)
        .parallel()
        .runOn(Schedulers.parallel())
        .flatMap(this::getFeedResponse)       // Flux<Feed>    
        .subscribe(t -> log.info("" + t));
		
		return "success";
	}

	private Mono<String> getFeedResponse(RequestDto dto) {
		String ipAddr = dto.getIpAddress();
		String name = dto.getName();
		
	    return webClient
            .get()
            .uri(uriBuilder -> uriBuilder
            	.path("/getClientIp")
            	.queryParam("name", name)
            	.build())
            .header("True-Client-IP", ipAddr)
            .retrieve()
            .bodyToMono(String.class) // Ideally, we should be able to use bodyToMono(JsonNode.class)
            ;
	}
	
	private Flux<RequestDto> createFlux(int count, String ipAddr, String name, String ipAddr2, String name2) {
		ArrayList<RequestDto> list = new ArrayList<RequestDto>(count);
		RequestDto dto = new RequestDto();
		dto.setIpAddress(ipAddr);
		dto.setName(name);
		
		RequestDto dto2 = new RequestDto();
		dto2.setIpAddress(ipAddr2);
		dto2.setName(name2);
		
		for (int i = 0; i < count; i++) {
			list.add(dto);
		}
		
		for (int i = 0; i < count; i++) {
			list.add(dto2);
		}
		
		return Flux.fromIterable(list);
	}
	
	private class RequestDto {
		String ipAddress;
		String name;
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
