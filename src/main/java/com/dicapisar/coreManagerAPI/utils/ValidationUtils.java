package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.exceptions.TypeStatusErrorException;

import java.util.Arrays;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.*;

public class ValidationUtils {
    public static void validateTypeStatus(String typeStatus) throws TypeStatusErrorException {
        List<String> listTypeStatus = Arrays.asList(STATUS_TRUE, STATUS_FALSE, STATUS_BOTH);

        if (!listTypeStatus.contains(typeStatus)) {
            throw new TypeStatusErrorException(typeStatus);
        }
    }
}
