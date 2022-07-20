package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.request.TypeItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.TypeItemResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.TypeItem;
import com.dicapisar.coreManagerAPI.models.User;

import java.time.LocalDateTime;

public class TypeItemUtils {
    public static TypeItemResponseDTO toTypeItemResponseDTO(TypeItem typeItem) {

        return new TypeItemResponseDTO(
                typeItem.getId(),
                typeItem.isActive(),
                typeItem.getName(),
                typeItem.isPerishable(),
                typeItem.getCreationDate(),
                typeItem.getUpdateDate(),
                typeItem.getCreator().getId(),
                typeItem.getCreator().getId()
        );
    }

    public static TypeItem toTypeItem(TypeItemCreateRequestDTO typeItemCreateRequestDTO, User userCreator) {
        TypeItem typeItem = new TypeItem();
        typeItem.setCreator(userCreator);
        typeItem.setUpdater(userCreator);
        typeItem.setCreationDate(LocalDateTime.now());
        typeItem.setUpdateDate(LocalDateTime.now());
        typeItem.setName(typeItemCreateRequestDTO.getName());
        typeItem.setActive(true);
        typeItem.setPerishable(typeItemCreateRequestDTO.getIsPerishable());
        return typeItem;
    }
}
