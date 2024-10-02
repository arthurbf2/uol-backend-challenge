package com.arthurbf.uol_backend.exceptions;

public class NoAvailableCodenameException extends RuntimeException {
    public NoAvailableCodenameException(){
        super("That group does not have any available codenames.");
    }
}
