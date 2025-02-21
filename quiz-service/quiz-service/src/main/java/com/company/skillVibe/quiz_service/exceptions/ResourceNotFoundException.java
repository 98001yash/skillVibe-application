package com.company.skillVibe.quiz_service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, String id, Long aLong){
        super(message);
    }
}
