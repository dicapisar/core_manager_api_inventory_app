package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.ItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.Item;
import com.dicapisar.coreManagerAPI.models.TypeItem;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.BrandRepository;
import com.dicapisar.coreManagerAPI.repository.ItemRepository;
import com.dicapisar.coreManagerAPI.repository.TypeItemRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.ItemUtils;
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
public class ItemService implements IItemService{
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private BrandRepository brandRepository;
    private TypeItemRepository typeItemRepository;

    public ItemResponseDTO getItemById(Long itemId) throws RecordNotFoundException {
        Item item = itemRepository.getItemByIdAAndIsActive(itemId);

        if (item == null) {
            throw new RecordNotFoundException("item", itemId);
        }

        return ItemUtils.toItemResponseDTO(item);

    }

    public ItemResponseDTO createNewItem(ItemCreateRequestDTO itemCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException, RecordNotFoundException {

        Item itemFounded = itemRepository.getItemByName(itemCreateRequestDTO.getName());

        if (itemFounded != null) {
            throw new RecordAlreadyExistedException("item", "name");
        }

        TypeItem typeItemFounded = typeItemRepository.getTypeItemByIdAndIsActive(itemCreateRequestDTO.getTypeItem());

        if (typeItemFounded == null) {
            throw new RecordNotFoundException("type item", itemCreateRequestDTO.getTypeItem());
        }

        Brand brandFounded = brandRepository.getBrandByIdAAndIsActive(itemCreateRequestDTO.getBrand());

        if (brandFounded == null) {
            throw new RecordNotFoundException("brand", itemCreateRequestDTO.getBrand());
        }

        User creator = userRepository.getUserById(idUserCreator);

        Item newItem = ItemUtils.toItemModel(itemCreateRequestDTO, brandFounded, typeItemFounded, creator);

        Item itemCreated = itemRepository.save(newItem);

        return ItemUtils.toItemResponseDTO(itemCreated);

    }

    public List<ItemResponseDTO> getListItems(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException {
        validateTypeStatus(typeStatus);

        List<Item> itemList;

        if (typeStatus.equals(STATUS_BOTH)) {
            itemList = this.getItemList();
        } else {
            itemList = this.getItemListByTypeStatus(typeStatus);
        }

        List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();

        for (Item item :
                itemList) {
            itemResponseDTOS.add(ItemUtils.toItemResponseDTO(item));
        }

        return itemResponseDTOS;

    }

    public ItemResponseDTO updateItemById(ItemUpdateRequestDTO itemUpdateRequestDTO, Long itemId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException {
        Item item = itemRepository.getItemByIdAAndIsActive(itemId);

        if (item == null) {
            throw new RecordNotFoundException("item", itemId);
        }

        Brand brand = brandRepository.getBrandByIdAAndIsActive(itemUpdateRequestDTO.getBrand());

        if (brand == null) {
            throw new RecordNotFoundException("brand", itemUpdateRequestDTO.getBrand());
        }

        TypeItem typeItem = typeItemRepository.getTypeItemByIdAndIsActive(itemUpdateRequestDTO.getTypeItem());

        if (typeItem == null) {
            throw new RecordNotFoundException("type item", itemUpdateRequestDTO.getTypeItem());
        }

        Item itemExistingInDataBase = itemRepository.getItemByName(itemUpdateRequestDTO.getName());

        if (itemExistingInDataBase != null && !itemExistingInDataBase.getId().equals(itemId)) {
            throw new RecordWithSameDataException("item", "name");
        }

        validateStatusActive(item);

        User updater = userRepository.getUserById(updaterId);

        setNewDataToItem(item, itemUpdateRequestDTO, brand, typeItem, updater);

        Item itemUpdated = itemRepository.save(item);

        return ItemUtils.toItemResponseDTO(itemUpdated);

    }

    public void changeStatusToItemById(Long itemId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException {

        Item item = itemRepository.getItemById(itemId);

        if (item == null) {
            throw new RecordNotFoundException("item", itemId);
        }

        if (item.isActive() == newStatus) {
            throw new RecordAlreadyHasStateException("item");
        }

        User updater = userRepository.getUserById(updaterId);

        changeStatusToItem(item, newStatus, updater);

        itemRepository.save(item);

    }

    private List<Item> getItemList() throws ListRecordNotFoundException {
       List<Item> itemList = itemRepository.getItemList();

       if (itemList.isEmpty()) {
           throw new ListRecordNotFoundException("item");
       }

       return itemList;
    }

    private List<Item> getItemListByTypeStatus(String typeStatus) throws ListRecordNotFoundException {
        List<Item> itemList = itemRepository.getItemListByTypeStatus(typeStatus.equals(STATUS_TRUE) ? Boolean.TRUE : Boolean.FALSE);

        if (itemList.isEmpty()) {
            throw new ListRecordNotFoundException("item");
        }

        return itemList;
    }

    private void setNewDataToItem(Item item, ItemUpdateRequestDTO itemUpdateRequestDTO, Brand brand, TypeItem typeItem, User updater) {
        item.setName(itemUpdateRequestDTO.getName());
        item.setPrice(itemUpdateRequestDTO.getPrice());
        item.setBrand(brand);
        item.setTypeItem(typeItem);
        item.setUpdater(updater);
        item.setUpdateDate(LocalDateTime.now());
    }

    private void changeStatusToItem(Item item, boolean newStatus, User updater) {
        item.setActive(newStatus);
        item.setUpdater(updater);
        item.setUpdateDate(LocalDateTime.now());
    }
}
