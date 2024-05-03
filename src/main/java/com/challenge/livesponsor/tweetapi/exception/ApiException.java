package com.challenge.livesponsor.tweetapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {
    private String message;
    private HttpStatus httpStatus;
}
