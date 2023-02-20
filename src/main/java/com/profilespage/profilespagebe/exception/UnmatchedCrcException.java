package com.profilespage.profilespagebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnmatchedCrcException extends ResponseStatusException {
    public UnmatchedCrcException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR ,message);
    }
}
