package com.challenge.livesponsor.tweetapi.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class AlreadyExistsException extends DomainException {
    private DomainRequest domainRequest;

    public AlreadyExistsException() {
        super(new DomainRequest(HttpStatus.CONFLICT.toString(), "Record already exists"));
    }

    public AlreadyExistsException(String message) {
        super(new DomainRequest(HttpStatus.CONFLICT.toString(), message));
    }

    public AlreadyExistsException(DomainRequest request) {
        super(request);
        this.domainRequest = request;
    }

    public AlreadyExistsException(String model, String value) {
        this(new DomainRequest(HttpStatus.CONFLICT.toString(), MessageFormat.format("{0} [{1}] already exists", model, value)));
    }
    public DomainRequest getDomainRequest() {
        return domainRequest;
    }
}
