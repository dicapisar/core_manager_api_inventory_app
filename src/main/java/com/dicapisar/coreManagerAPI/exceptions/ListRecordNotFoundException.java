package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class ListRecordNotFoundException extends CoreManagerException{

    public ListRecordNotFoundException(String typeRecord) {
        super("The list of '" + typeRecord + "' was not found.", HttpStatus.NOT_FOUND);
    }
}
