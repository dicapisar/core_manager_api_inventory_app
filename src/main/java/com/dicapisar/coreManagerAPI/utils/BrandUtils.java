package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;

public class BrandUtils {
    public static BrandResponseDTO toBrandResponseDTO(Brand brand) {

        BrandResponseDTO brandResponseDTO = new BrandResponseDTO(
                brand.getId(),
                brand.isActive(),
                brand.getName(),
                brand.getCreationDate(),
                brand.getUpdateDate(),
                brand.getCreator().getId(),
                brand.getCreator().getId()
        );
        return brandResponseDTO;
    }
}
