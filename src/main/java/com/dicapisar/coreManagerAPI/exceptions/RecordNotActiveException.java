package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotActiveException extends CoreManagerException{

    public RecordNotActiveException(String typeRecord, Long idRecord) {
        super("The " + typeRecord + " record with id " + idRecord.toString() + " is deactivated, action is not possible.", HttpStatus.BAD_REQUEST);
    }
}
