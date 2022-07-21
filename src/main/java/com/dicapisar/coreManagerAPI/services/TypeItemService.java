package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.TypeItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.TypeItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.models.TypeItem;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.TypeItemRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
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
public class TypeItemService implements ITypeItemService {
    private TypeItemRepository typeItemRepository;
    private UserRepository userRepository;

    public TypeItemResponseDTO getTypeItemById(Long typeItemId) throws RecordNotFoundException {
        TypeItem typeItem = typeItemRepository.getTypeItemByIdAndIsActive(typeItemId);

        if (typeItem == null) {
            throw new RecordNotFoundException("type item", typeItemId);
        }

        return TypeItemUtils.toTypeItemResponseDTO(typeItem);
    }

    public TypeItemResponseDTO createNewTypeItem(TypeItemCreateRequestDTO typeItemCreateRequestDTO, Long idUserCreator) throws RecordAlreadyExistedException {
        TypeItem typeItemFounded = typeItemRepository.getTypeItemByName(typeItemCreateRequestDTO.getName());

        if (typeItemFounded != null) {
            throw new RecordAlreadyExistedException("type item", "name");
        }

        User creator = userRepository.getUserById(idUserCreator);

        TypeItem newTypeItem = TypeItemUtils.toTypeItem(typeItemCreateRequestDTO, creator);

        TypeItem typeItemCreated = typeItemRepository.save(newTypeItem);

        return TypeItemUtils.toTypeItemResponseDTO(typeItemCreated);
    }

    public List<TypeItemResponseDTO> getListTypeItemss(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException {
        validateTypeStatus(typeStatus);

        List<TypeItem> typeItemList;

        if (typeStatus.equals(STATUS_BOTH)) {
            typeItemList = this.getTypeItemList();
        } else {
            typeItemList = this.getTypeItemListByTypeStatus(typeStatus);
        }

        List<TypeItemResponseDTO> typeItemResponseDTOS = new ArrayList<>();

        for (TypeItem typeItem :
                typeItemList) {
            typeItemResponseDTOS.add(TypeItemUtils.toTypeItemResponseDTO(typeItem));
        }

        return typeItemResponseDTOS;

    }

    public TypeItemResponseDTO updateTypeItemById(TypeItemUpdateRequestDTO typeItemUpdateRequestDTO, Long typeItemId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException {
        TypeItem typeItem = typeItemRepository.getTypeItemByIdAndIsActive(typeItemId);

        if (typeItem == null) {
            throw new RecordNotFoundException("type item", typeItemId);
        }

        TypeItem typeItemExistingInDataBase = typeItemRepository.getTypeItemByName(typeItemUpdateRequestDTO.getName());

        if (typeItemExistingInDataBase != null && !typeItemExistingInDataBase.getId().equals(typeItemId)) {
            throw new RecordWithSameDataException("brand", "name");
        }

        validateStatusActive(typeItem);

        User updater = userRepository.getUserById(updaterId);

        setNewDataToTypeItem(typeItem, typeItemUpdateRequestDTO, updater);

        TypeItem typeItemUpdated = typeItemRepository.save(typeItem);

        return TypeItemUtils.toTypeItemResponseDTO(typeItemUpdated);

    }

    public void changeStatusToTypeItemById(Long typeItemId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException {

        TypeItem typeItem = typeItemRepository.getTypeItemById(typeItemId);

        if (typeItem == null) {
            throw new RecordNotFoundException("type item", typeItemId);
        }

        if (typeItem.isActive() == newStatus) {
            throw new RecordAlreadyHasStateException("type item");
        }

        User updater = userRepository.getUserById(updaterId);

        changeStatusToTypeItem(typeItem, newStatus, updater);

        typeItemRepository.save(typeItem);

    }

    private List<TypeItem> getTypeItemList() throws ListRecordNotFoundException {
        List<TypeItem> typeItemList = typeItemRepository.getTypeItemList();

        if (typeItemList.isEmpty()) {
            throw new ListRecordNotFoundException("type items");
        }

        return typeItemList;
    }

    private List<TypeItem> getTypeItemListByTypeStatus(String typeStatus) throws ListRecordNotFoundException {
        List<TypeItem> brandList = typeItemRepository.getTypeItemListByTypeStatus(typeStatus.equals(STATUS_TRUE) ? Boolean.TRUE : Boolean.FALSE);

        if (brandList.isEmpty()) {
            throw new ListRecordNotFoundException("brand");
        }

        return brandList;
    }

    private void setNewDataToTypeItem(TypeItem typeItem, TypeItemUpdateRequestDTO typeItemUpdateRequestDTO, User updater) {
        typeItem.setUpdateDate(LocalDateTime.now());
        typeItem.setName(typeItemUpdateRequestDTO.getName());
        typeItem.setPerishable(typeItemUpdateRequestDTO.getIsPerishable());
        typeItem.setUpdater(updater);
    }

    private void changeStatusToTypeItem(TypeItem typeItem, boolean newStatus, User updater) {
        typeItem.setActive(newStatus);
        typeItem.setUpdater(updater);
        typeItem.setUpdateDate(LocalDateTime.now());
    }
}
