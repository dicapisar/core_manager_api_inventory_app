package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.exceptions.RecordNotActiveException;
import com.dicapisar.coreManagerAPI.exceptions.TypeStatusErrorException;
import com.dicapisar.coreManagerAPI.models.*;

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

    public static void validateStatusActive(Brand brand) throws RecordNotActiveException {
        if (!brand.isActive()) {
            throw new RecordNotActiveException("brand", brand.getId());
        }
    }

    public static void validateStatusActive(TypeItem typeItem) throws RecordNotActiveException {
        if (!typeItem.isActive()) {
            throw new RecordNotActiveException("typeItem", typeItem.getId());
        }
    }

    public static void validateStatusActive(Provider provider) throws RecordNotActiveException {
        if (!provider.isActive()) {
            throw new RecordNotActiveException("typeItem", provider.getId());
        }
    }

    public static void validateStatusActive(Contact contact) throws RecordNotActiveException {
        if (!contact.isActive()) {
            throw new RecordNotActiveException("contact", contact.getId());
        }
    }

    public static void validateStatusActive(Item item) throws RecordNotActiveException {
        if (!item.isActive()) {
            throw new RecordNotActiveException("item", item.getId());
        }
    }
}
