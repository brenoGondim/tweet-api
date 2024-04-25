package com.challenge.livesponsor.tweetapi.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class NotFoundException extends DomainException {
    private DomainRequest domainRequest;

    public NotFoundException() {
        super(new DomainRequest(HttpStatus.NOT_FOUND.toString(), "Record not found"));
    }

    public NotFoundException(String message) {
        super(new DomainRequest(HttpStatus.NOT_FOUND.toString(), message));
    }

    public NotFoundException(DomainRequest request) {
        super(request);
        this.domainRequest = request;
    }

    public NotFoundException(String model, String value) {
        this(new DomainRequest(HttpStatus.NOT_FOUND.toString(), MessageFormat.format(model, value)));
    }
    public DomainRequest getDomainRequest() {
        return domainRequest;
    }
}
