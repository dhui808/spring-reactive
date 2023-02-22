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

    @GetMapping("/chat-blocking")
    public List<JsonNode> getChatBlocking() {

        return service.getChatBlocking();
    }

    @GetMapping(value = "/chat-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<JsonNode> getChatNonBlocking() {

        return service.getChatNonBlocking();
    }



}
