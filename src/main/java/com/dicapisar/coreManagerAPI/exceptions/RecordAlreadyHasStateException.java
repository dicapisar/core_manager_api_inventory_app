package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class RecordAlreadyHasStateException extends CoreManagerException{

    public RecordAlreadyHasStateException(String typeRecord) {
        super("The " + typeRecord + " record already has request status.", HttpStatus.BAD_REQUEST);
    }
}
