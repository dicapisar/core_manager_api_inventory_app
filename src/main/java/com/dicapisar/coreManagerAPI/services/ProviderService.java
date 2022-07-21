package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ProviderCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ProviderUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ProviderResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.TypeItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.models.Provider;
import com.dicapisar.coreManagerAPI.models.TypeItem;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.ProviderRepository;
import com.dicapisar.coreManagerAPI.repository.TypeItemRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.ProviderUtils;
import com.dicapisar.coreManagerAPI.utils.TypeItemUtils;
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
public class ProviderService implements IProviderService {
    private ProviderRepository providerRepository;
    private UserRepository userRepository;

    public ProviderResponseDTO getProviderById(Long providerId) throws RecordNotFoundException {
        Provider provider = providerRepository.getProviderByIdAAndIsActive(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        return ProviderUtils.toProviderResponseDTO(provider);
    }

    public ProviderResponseDTO createNewProvider(ProviderCreateRequestDTO providerCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException {

        Provider providerFounded = providerRepository.getProviderByName(providerCreateRequestDTO.getName());

        if (providerFounded != null) {
            throw new RecordAlreadyExistedException("provider", "name");
        }

        User creator = userRepository.getUserById(idUserCreator);

        Provider newProvider = ProviderUtils.toProviderModel(providerCreateRequestDTO, creator);

        Provider providerCreated = providerRepository.save(newProvider);

        return ProviderUtils.toProviderResponseDTO(providerCreated);
    }

    public List<ProviderResponseDTO> getListProviders(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException {

        validateTypeStatus(typeStatus);

        List<Provider> providerList;

        if (typeStatus.equals(STATUS_BOTH)) {
            providerList = this.getProviderList();
        } else {
            providerList = this.getProviderByTypeStatus(typeStatus);
        }

        List<ProviderResponseDTO> providerResponseDTOS = new ArrayList<>();

        for (Provider provider :
                providerList) {
            providerResponseDTOS.add(ProviderUtils.toProviderResponseDTO(provider));
        }

        return providerResponseDTOS;

    }

    public ProviderResponseDTO updateProviderById(ProviderUpdateRequestDTO providerUpdateRequestDTO, Long providerId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException {
        Provider provider = providerRepository.getProviderByIdAAndIsActive(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        Provider providerExistingInDataBase = providerRepository.getProviderByName(providerUpdateRequestDTO.getName());

        if (providerExistingInDataBase != null && !providerExistingInDataBase.getId().equals(providerId)) {
            throw new RecordWithSameDataException("provider", "name");
        }

        validateStatusActive(provider);

        User updater = userRepository.getUserById(updaterId);

        setNewDataToProvider(provider, providerUpdateRequestDTO, updater);

        Provider providerUpdated = providerRepository.save(provider);

        return ProviderUtils.toProviderResponseDTO(providerUpdated);

    }

    public void changeStatusToTypeProviderById(Long providerId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException {

        Provider provider = providerRepository.getProviderById(providerId);

        if (provider == null) {
            throw new RecordNotFoundException("provider", providerId);
        }

        if (provider.isActive() == newStatus) {
            throw new RecordAlreadyHasStateException("provider");
        }

        User updater = userRepository.getUserById(updaterId);

        changeStatusToProvider(provider, newStatus, updater);

        providerRepository.save(provider);

    }

    private List<Provider> getProviderList() throws ListRecordNotFoundException {
        List<Provider> providerList = providerRepository.getProviderList();

        if (providerList.isEmpty()) {
            throw new ListRecordNotFoundException("provider");
        }

        return providerList;
    }

    private List<Provider> getProviderByTypeStatus(String typeStatus) throws ListRecordNotFoundException {
        List<Provider> providerList = providerRepository.getProviderListByTypeStatus(typeStatus.equals(STATUS_TRUE) ? Boolean.TRUE : Boolean.FALSE);

        if (providerList.isEmpty()) {
            throw new ListRecordNotFoundException("provider");
        }

        return providerList;
    }

    private void setNewDataToProvider(Provider provider, ProviderUpdateRequestDTO providerUpdateRequestDTO, User updater) {
        provider.setUpdateDate(LocalDateTime.now());
        provider.setUpdater(updater);
        provider.setName(providerUpdateRequestDTO.getName());
        provider.setAddress(providerUpdateRequestDTO.getAddress());
        provider.setDocumentNumber(providerUpdateRequestDTO.getDocumentNumber());
        provider.setEmail(providerUpdateRequestDTO.getEmail());
        provider.setPhoneNumber(providerUpdateRequestDTO.getPhoneNumber());
    }

    private void changeStatusToProvider(Provider provider, boolean newStatus, User updater) {
        provider.setActive(newStatus);
        provider.setUpdater(updater);
        provider.setUpdateDate(LocalDateTime.now());
    }
}
