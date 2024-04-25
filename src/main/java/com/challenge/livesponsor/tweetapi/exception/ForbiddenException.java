package com.challenge.livesponsor.tweetapi.exception;

import java.text.MessageFormat;

public class ForbiddenException extends DomainException {
    public ForbiddenException() {
        super("Value not found");
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String item, String value) {
        super(MessageFormat.format("{0} {1} forbidden", item, value));
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
