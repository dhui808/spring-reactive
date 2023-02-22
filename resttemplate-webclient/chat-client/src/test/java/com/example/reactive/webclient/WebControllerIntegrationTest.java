package com.example.reactive.webclient;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.JsonNode;

@DirtiesContext(classMode = BEFORE_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = WebClientApplication.class)
class WebControllerIntegrationTest {

    private WebTestClient testClient;

    private int volume = 100;
    
    @BeforeEach
    void setup() {
    	String baseUri = "http://localhost:8080";
    	testClient = WebTestClient.bindToServer().baseUrl(baseUri).build();
    }
	
    @Test
    void whenEndpointWithBlockingClientIsCalled_thenThreeTweetsAreReceived() {
    	long startTime = System.currentTimeMillis();
        for (int i = 0; i < volume; i++) {
	    	testClient.get()
	          .uri("/chat-blocking")
	          .exchange()
	          .expectStatus().isOk()
	          .expectBodyList(JsonNode.class).hasSize(3);
        }
        
    	long endTime = System.currentTimeMillis();
        System.err.println("Time for RestTemplate:" + (endTime-startTime));
    }

    @Test
    void whenEndpointWithNonBlockingClientIsCalled_thenThreeTweetsAreReceived() {
    	long startTime = System.currentTimeMillis();
        for (int i = 0; i < volume; i++) {
    	testClient.get()
          .uri("/chat-non-blocking")
          .exchange()
          .expectStatus().isOk()
          .expectBodyList(JsonNode.class).hasSize(3);
        }
        
    	long endTime = System.currentTimeMillis();
        System.err.println("Time for WebClient:" + (endTime-startTime));
        }
}