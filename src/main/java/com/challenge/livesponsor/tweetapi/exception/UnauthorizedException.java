package com.challenge.livesponsor.tweetapi.exception;

import java.text.MessageFormat;

public class UnauthorizedException extends DomainException {
    public UnauthorizedException() {
        super("Value not found");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String item, String value) {
        super(MessageFormat.format("{0} {1} unauthorized", item, value));
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
