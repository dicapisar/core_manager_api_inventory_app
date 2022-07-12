package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.User;

import java.time.LocalDateTime;

public class BrandUtils {
    public static BrandResponseDTO toBrandResponseDTO(Brand brand) {

        return new BrandResponseDTO(
                brand.getId(),
                brand.isActive(),
                brand.getName(),
                brand.getCreationDate(),
                brand.getUpdateDate(),
                brand.getCreator().getId(),
                brand.getCreator().getId()
        );
    }

    public static Brand toBrandModel(String name, User userCreator) {
        Brand brand = new Brand();
        brand.setCreator(userCreator);
        brand.setUpdater(userCreator);
        brand.setCreationDate(LocalDateTime.now());
        brand.setUpdateDate(LocalDateTime.now());
        brand.setName(name);
        brand.setActive(true);
        return brand;
    }
}
