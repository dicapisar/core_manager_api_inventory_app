package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;

import java.util.List;



public interface IItemService {

    ItemResponseDTO getItemById(Long itemId) throws RecordNotFoundException;

    ItemResponseDTO createNewItem(ItemCreateRequestDTO itemCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException, RecordNotFoundException;

    List<ItemResponseDTO> getListItems(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException;

    ItemResponseDTO updateItemById(ItemUpdateRequestDTO itemUpdateRequestDTO, Long itemId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException;

    void changeStatusToItemById(Long itemId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException;

}
