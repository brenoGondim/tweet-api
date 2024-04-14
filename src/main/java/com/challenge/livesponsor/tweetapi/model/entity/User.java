package com.challenge.livesponsor.tweetapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String email;
    private String name;
}
