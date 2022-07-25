package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ContactCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ContactUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ContactResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.models.Contact;
import com.dicapisar.coreManagerAPI.models.Provider;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.ContactRepository;
import com.dicapisar.coreManagerAPI.repository.ProviderRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.ContactUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_BOTH;
import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_TRUE;
import static com.dicapisar.coreManagerAPI.utils.ValidationUtils.validateStatusActive;
import static com.dicapisar.coreManagerAPI.utils.ValidationUtils.validateTypeStatus;

@Service
@AllArgsConstructor
public class ContactService implements IContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private ProviderRepository providerRepositoryImp;

    public ContactResponseDTO getContactByIdAndProviderId(Long contactId, Long providerId) throws RecordNotFoundException {

        Provider provider = providerRepositoryImp.getProviderByIdAAndIsActive(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        Contact contact = contactRepository.getContactByIdAAndIsActiveAndProviderId(contactId, providerId);

        if (contact == null) {
            throw new RecordNotFoundException("contact", contactId);
        }

        return ContactUtils.toContactResponseDTO(contact);
    }

    public ContactResponseDTO createNewContactByProviderId(ContactCreateRequestDTO contactCreateRequestDTO, Long providerId, Long idUserCreator)
            throws RecordAlreadyExistedException, RecordNotFoundException {

        Provider provider = providerRepositoryImp.getProviderByIdAAndIsActive(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        Contact contactFounded = contactRepository.getContactByNameAndProviderId(contactCreateRequestDTO.getName(), providerId);

        if (contactFounded != null) {
            throw new RecordAlreadyExistedException("contact", "name");
        }

        User creator = userRepository.getUserById(idUserCreator);

        Contact newContact = ContactUtils.toContactModel(contactCreateRequestDTO, provider, creator);

        Contact contactCreated = contactRepository.save(newContact);

        return ContactUtils.toContactResponseDTO(contactCreated);
    }

    public List<ContactResponseDTO> getListContactByProviderId(Long providerId, String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException, RecordNotFoundException {

        Provider provider = providerRepositoryImp.getProviderByIdAAndIsActive(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        validateTypeStatus(typeStatus);

        List<Contact> contactList;

        if (typeStatus.equals(STATUS_BOTH)) {
            contactList = this.getContactList(providerId);
        } else {
            contactList = this.getContactByTypeStatusAndProviderId(typeStatus, providerId);
        }

        List<ContactResponseDTO> contactResponseDTOList = new ArrayList<>();

        for (Contact contact :
                contactList) {
            contactResponseDTOList.add(ContactUtils.toContactResponseDTO(contact));
        }

        return contactResponseDTOList;

    }

    public ContactResponseDTO updateContactByIdAndProviderId(ContactUpdateRequestDTO contactUpdateRequestDTO, Long contactId,Long providerIdIm, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException {

        Provider provider = providerRepositoryImp.getProviderByIdAAndIsActive(providerIdIm);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerIdIm);
        }

        Contact contact = contactRepository.getContactByIdAAndIsActiveAndProviderId(contactId, providerIdIm);

        if (contact == null) {
            throw new RecordNotFoundException("contact", contactId);
        }

        Contact contactExistingInDataBase = contactRepository.getContactByNameAndProviderId(contactUpdateRequestDTO.getName(), providerIdIm);

        if (contactExistingInDataBase != null && !contactExistingInDataBase.getId().equals(contactId)) {
            throw new RecordWithSameDataException("contact", "name");
        }

        validateStatusActive(contact);

        User updater = userRepository.getUserById(updaterId);

        setNewDataToContact(contact, contactUpdateRequestDTO, provider,updater);

        Contact contactUpdated = contactRepository.save(contact);

        return ContactUtils.toContactResponseDTO(contactUpdated);

    }

    public void changeStatusToContactById(Long contactId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException {

        Contact contact = contactRepository.getContactById(contactId);

        if (contact == null) {
            throw new RecordNotFoundException("contact", contactId);
        }

        if (contact.isActive() == newStatus) {
            throw new RecordAlreadyHasStateException("contact");
        }

        User updater = userRepository.getUserById(updaterId);

        changeStatusToContact(contact, newStatus, updater);

        contactRepository.save(contact);

    }

    private List<Contact> getContactList(Long providerId) throws ListRecordNotFoundException {
        List<Contact> contactList = contactRepository.getContactListByProviderId(providerId);

        if (contactList.isEmpty()) {
            throw new ListRecordNotFoundException("contact");
        }

        return contactList;
    }

    private List<Contact> getContactByTypeStatusAndProviderId(String typeStatus, Long providerId) throws ListRecordNotFoundException {
        List<Contact> contactList = contactRepository.getContactListByTypeStatusAndProviderId(typeStatus.equals(STATUS_TRUE) ? Boolean.TRUE : Boolean.FALSE, providerId);

        if (contactList.isEmpty()) {
            throw new ListRecordNotFoundException("contact");
        }

        return contactList;
    }

    private void setNewDataToContact(Contact contact, ContactUpdateRequestDTO contactUpdateRequestDTO, Provider provider,User updater) {
        contact.setName(contactUpdateRequestDTO.getName());
        contact.setPhoneNumber(contactUpdateRequestDTO.getPhoneNumber());
        contact.setEmail(contactUpdateRequestDTO.getEmail());
        contact.setUpdater(updater);
        contact.setUpdateDate(LocalDateTime.now());
        contact.setProvider(provider);
    }

    private void changeStatusToContact(Contact contact, boolean newStatus, User updater) {
        contact.setActive(newStatus);
        contact.setUpdater(updater);
        contact.setUpdateDate(LocalDateTime.now());
    }
}
