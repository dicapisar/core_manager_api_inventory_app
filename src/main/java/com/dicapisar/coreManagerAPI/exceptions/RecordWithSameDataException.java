package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class RecordWithSameDataException extends CoreManagerException{

    public RecordWithSameDataException(String typeRecord, String attribute) {
        super("Another " + typeRecord + " already exists with the same '" + attribute + "'.", HttpStatus.BAD_REQUEST);
    }
}
