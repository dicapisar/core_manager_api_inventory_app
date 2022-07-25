package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ContactCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ContactUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ContactResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;

import java.util.List;


public interface IContactService {


    public ContactResponseDTO getContactByIdAndProviderId(Long contactId, Long providerId) throws RecordNotFoundException;

    public ContactResponseDTO createNewContactByProviderId(ContactCreateRequestDTO contactCreateRequestDTO, Long providerId, Long idUserCreator)
            throws RecordAlreadyExistedException, RecordNotFoundException;

    public List<ContactResponseDTO> getListContactByProviderId(Long providerId, String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException, RecordNotFoundException;

    public ContactResponseDTO updateContactByIdAndProviderId(ContactUpdateRequestDTO contactUpdateRequestDTO, Long contactId,Long providerIdIm, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException;

    public void changeStatusToContactById(Long contactId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException;

}
