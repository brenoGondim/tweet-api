package com.challenge.livesponsor.tweetapi.exception;

public abstract class DomainException extends RuntimeException {

    private DomainRequest domainRequest;
    protected DomainException(String message) {
        super(message);
    }
    protected DomainException(DomainRequest request) {
        super(request.getMessage());
        this.domainRequest = request;
    }

    public DomainRequest getDomainRequest() {
        return domainRequest;
    }

}