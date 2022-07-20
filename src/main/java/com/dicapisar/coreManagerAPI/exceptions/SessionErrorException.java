package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class SessionErrorException extends CoreManagerException{
    public SessionErrorException() {
        super("Session error, try to login again.", HttpStatus.FORBIDDEN);
    }
}
