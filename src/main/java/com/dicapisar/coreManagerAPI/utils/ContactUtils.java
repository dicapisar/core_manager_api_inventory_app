package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.request.ContactCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ContactResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.Contact;
import com.dicapisar.coreManagerAPI.models.Provider;
import com.dicapisar.coreManagerAPI.models.User;

import java.time.LocalDateTime;

public class ContactUtils {
    public static ContactResponseDTO toContactResponseDTO(Contact contact) {

        return new ContactResponseDTO(
                contact.getId(),
                contact.isActive(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getEmail(),
                contact.getCreationDate(),
                contact.getUpdateDate(),
                contact.getProvider().getId(),
                contact.getCreator().getId(),
                contact.getUpdater().getId()
        );
    }

    public static Contact toContactModel(ContactCreateRequestDTO contactCreateRequestDTO, Provider provider, User userCreator) {
        Contact contact = new Contact();
        contact.setActive(true);
        contact.setName(contactCreateRequestDTO.getName());
        contact.setEmail(contactCreateRequestDTO.getEmail());
        contact.setPhoneNumber(contactCreateRequestDTO.getPhoneNumber());
        contact.setProvider(provider);
        contact.setCreationDate(LocalDateTime.now());
        contact.setUpdateDate(LocalDateTime.now());
        contact.setUpdater(userCreator);
        contact.setCreator(userCreator);

        return contact;

    }
}
