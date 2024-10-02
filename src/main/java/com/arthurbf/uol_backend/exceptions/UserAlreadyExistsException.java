package com.arthurbf.uol_backend.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(){
        super("There already is an user with this email");
    }
}
