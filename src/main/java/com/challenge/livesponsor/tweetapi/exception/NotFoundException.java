package com.challenge.livesponsor.tweetapi.exception;

import java.text.MessageFormat;

public class NotFoundException extends DomainException {
    public NotFoundException() {
        super("Value not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String item, String value) {
        super(MessageFormat.format("{0} {1} not found", item, value));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
