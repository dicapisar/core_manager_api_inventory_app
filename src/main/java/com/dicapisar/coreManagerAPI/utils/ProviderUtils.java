package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.request.ProviderCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ProviderResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.Provider;
import com.dicapisar.coreManagerAPI.models.User;

import java.time.LocalDateTime;

public class ProviderUtils {
    public static ProviderResponseDTO toProviderResponseDTO(Provider provider) {

        return new ProviderResponseDTO(
                provider.getId(),
                provider.isActive(),
                provider.getName(),
                provider.getAddress(),
                provider.getDocumentNumber(),
                provider.getEmail(),
                provider.getPhoneNumber(),
                provider.getCreationDate(),
                provider.getUpdateDate(),
                provider.getCreator().getId(),
                provider.getCreator().getId()
        );
    }

    public static Provider toProviderModel(ProviderCreateRequestDTO providerCreateRequestDTO, User userCreator) {
        Provider provider = new Provider();
        provider.setCreator(userCreator);
        provider.setUpdater(userCreator);
        provider.setCreationDate(LocalDateTime.now());
        provider.setUpdateDate(LocalDateTime.now());
        provider.setName(providerCreateRequestDTO.getName());
        provider.setActive(true);
        provider.setAddress(providerCreateRequestDTO.getAddress());
        provider.setDocumentNumber(providerCreateRequestDTO.getDocumentNumber());
        provider.setEmail(providerCreateRequestDTO.getEmail());
        provider.setPhoneNumber(providerCreateRequestDTO.getPhoneNumber());
        return provider;
    }
}
