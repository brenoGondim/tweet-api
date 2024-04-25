package com.challenge.livesponsor.tweetapi.exception;


import java.text.MessageFormat;

public class AlreadyExistsException extends DomainException {
    public AlreadyExistsException() {
        super("Value already exists");
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String item, String value) {
        super(MessageFormat.format("{0} {1} already exists", item, value));
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
