package com.challenge.livesponsor.tweetapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DomainRequest {
    private String code;
    private String message;
}