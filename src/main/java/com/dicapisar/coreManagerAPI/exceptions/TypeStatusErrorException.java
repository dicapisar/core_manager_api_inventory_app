package com.dicapisar.coreManagerAPI.exceptions;

import org.springframework.http.HttpStatus;

public class TypeStatusErrorException extends CoreManagerException {

    public TypeStatusErrorException(String typeStatus) {
        super("The type status" + typeStatus + "is not valid", HttpStatus.BAD_REQUEST);
    }
}
