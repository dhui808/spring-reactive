package com.example.reactive.webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ChatServiceController {

    @GetMapping("/chat")
    private List<Chat> getAllTweets() throws Exception {
        Thread.sleep(1000);
        return Arrays.asList(
          new Chat("World Cup 2022 ᘘ", "@user1"),
          new Chat("Leo Messi", "@user2"),
          new Chat("Spring WebClient ᘘ", "@user3"));
    }
}
