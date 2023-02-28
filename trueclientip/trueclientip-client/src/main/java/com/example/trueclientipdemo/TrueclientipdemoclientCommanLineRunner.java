package com.example.trueclientipdemo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TrueclientipdemoclientCommanLineRunner implements CommandLineRunner {

	@Value("${host}")
	String host;
	
	@Value("${count:10}")
	int count;

	@Value("${clientIp:1.2.3.4}")
	String clientIp;
	
	@Value("${firstname:joe}")
	String firstname;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {
		makeCall(count, clientIp, firstname);
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
	    
	    String s = makeCall(size, ipAddr, fn);
	    
	    return s;
	}

	private String makeCall(int size, String ipAddr, String name) {
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		
		for (int i  = 0; i < size; i++) {
			headers.set("True-Client-IP", ipAddr);
			
			String url = host + "/getClientIp";
			Map<String, String> params = new HashMap<String, String>();
			URI uri = UriComponentsBuilder.fromUriString(url)
			        .buildAndExpand(params)
			        .toUri();
			uri = UriComponentsBuilder
			        .fromUri(uri)
			        .queryParam("name", name)
			        .build()
			        .toUri();
			ResponseEntity<String> clientIp = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			log.info(clientIp.getBody());
		}
		
		return "success";
	}
}
