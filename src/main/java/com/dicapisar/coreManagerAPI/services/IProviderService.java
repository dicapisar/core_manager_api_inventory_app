package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ProviderCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ProviderUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ProviderResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.models.Provider;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.ProviderRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.ProviderUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_BOTH;
import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_TRUE;
import static com.dicapisar.coreManagerAPI.utils.ValidationUtils.validateStatusActive;
import static com.dicapisar.coreManagerAPI.utils.ValidationUtils.validateTypeStatus;

public interface IProviderService {

    ProviderResponseDTO getProviderById(Long providerId) throws RecordNotFoundException;
    ProviderResponseDTO createNewProvider(ProviderCreateRequestDTO providerCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException;
    List<ProviderResponseDTO> getListProviders(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException;
    ProviderResponseDTO updateProviderById(ProviderUpdateRequestDTO providerUpdateRequestDTO, Long providerId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException;
    void changeStatusToTypeProviderById(Long providerId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException;
}
