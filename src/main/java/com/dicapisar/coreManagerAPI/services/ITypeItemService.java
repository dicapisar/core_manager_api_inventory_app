package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.TypeItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.TypeItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;

import java.util.List;

public interface ITypeItemService {
    TypeItemResponseDTO getTypeItemById(Long typeItemId) throws RecordNotFoundException;
    TypeItemResponseDTO createNewTypeItem(TypeItemCreateRequestDTO typeItemCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException;
    List<TypeItemResponseDTO> getListTypeItemss(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException;
    TypeItemResponseDTO updateTypeItemById(TypeItemUpdateRequestDTO typeItemUpdateRequestDTO, Long typeItemId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException;
    void changeStatusToTypeItemById(Long typeItemId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException;
}
