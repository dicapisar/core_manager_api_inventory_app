package com.dicapisar.coreManagerAPI.exceptions;

import com.dicapisar.coreManagerAPI.dtos.ErrorDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoreManagerException extends Exception {
    private final ErrorDTO error;
    private final HttpStatus status;

    public CoreManagerException(String message, HttpStatus status) {
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setErrorName(this.getClass().getName());
        this.status = status;
    }
}
