package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends CoreManagerException{

    public RecordNotFoundException(String typeRecord, Long idRecord) {
        super("The " + typeRecord + " record with id " + idRecord.toString() + " was not found.", HttpStatus.NOT_FOUND);
    }
}
