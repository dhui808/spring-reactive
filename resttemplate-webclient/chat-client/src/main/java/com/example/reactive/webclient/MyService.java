package com.example.reactive.webclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class MyService {
	
    @Value(value="${chat-service.port:8081}")
    private int serverPort;

    public List<JsonNode> getChatBlocking() {

        final String uri = getSlowServiceUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<JsonNode>> response = restTemplate.exchange(
          uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<List<JsonNode>>(){});

        List<JsonNode> result = response.getBody();
        result.forEach(chat -> log.debug(chat.toString()));

        return result;
    }

    public Flux<JsonNode> getChatNonBlocking() {

        Flux<JsonNode> chatFlux = WebClient.create()
          .get()
          .uri(getSlowServiceUri())
          .retrieve()
          .bodyToFlux(JsonNode.class);

        chatFlux.subscribe(chat -> log.debug(chat.toString()));
        return chatFlux;
    }

    private String getSlowServiceUri() {
        return "http://localhost:" + serverPort + "/chat";
    }
}
