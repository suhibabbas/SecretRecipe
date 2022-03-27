package com.example.AuthForSecretRecipe.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChefNotFoundException extends RuntimeException {
    public ChefNotFoundException() {
    }

    public ChefNotFoundException(String message){
        super(message);
    }
}
