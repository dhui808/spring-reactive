package com.example.reactive.webclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Flux;

@RestController
public class WebController {

    @Autowired
    private MyService service;

    @GetMapping(value="/chat-blocking", produces = "application/json;charset=UTF-8")
    public List<JsonNode> getChatBlocking() {

        return service.getChatBlocking();
    }

    @GetMapping(value = "/chat-non-blocking", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<JsonNode> getChatNonBlocking() {

        return service.getChatNonBlocking();
    }



}
