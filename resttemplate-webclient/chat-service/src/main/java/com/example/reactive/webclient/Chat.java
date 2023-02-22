package com.example.reactive.webclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	@NonNull
    private String text;
	@NonNull
    private String username;
}
