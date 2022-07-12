package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class RecordAlreadyExistedException extends CoreManagerException {

    public RecordAlreadyExistedException(String typeRecord, String nameAttribute) {
        super("A " + typeRecord + " record with the same '" + nameAttribute + "' attribute already exists. ",
                HttpStatus.BAD_REQUEST);
    }
}
