package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.dtos.request.ItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ItemResponseDTO;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.Item;
import com.dicapisar.coreManagerAPI.models.TypeItem;
import com.dicapisar.coreManagerAPI.models.User;

import java.time.LocalDateTime;

public class ItemUtils {
    public static ItemResponseDTO toItemResponseDTO(Item item) {

        return new ItemResponseDTO(
                item.getId(),
                item.isActive(),
                item.getStatus(),
                item.getName(),
                item.getCount(),
                item.getPrice(),
                item.getBrand().getId(),
                item.getTypeItem().getId(),
                item.getCreationDate(),
                item.getUpdateDate(),
                item.getCreator().getId(),
                item.getUpdater().getId()
        );

    }

    public static Item toItemModel(ItemCreateRequestDTO itemCreateRequestDTO, Brand brand, TypeItem typeItem, User userCreator) {
        Item item = new Item();

        item.setActive(true);
        item.setStatus("test");
        item.setName(itemCreateRequestDTO.getName());
        item.setCount(0);
        item.setPrice(itemCreateRequestDTO.getPrice());
        item.setBrand(brand);
        item.setTypeItem(typeItem);
        item.setCreationDate(LocalDateTime.now());
        item.setUpdateDate(LocalDateTime.now());
        item.setCreator(userCreator);
        item.setUpdater(userCreator);

        return item;
    }
}
