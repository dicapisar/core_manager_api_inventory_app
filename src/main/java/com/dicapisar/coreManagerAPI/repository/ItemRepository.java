package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.Item;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i where i.id =:itemId and i.isActive = true")
    Item getItemByIdAAndIsActive(@Param("itemId") Long itemId);

    @Query("select i from Item i where i.name =:itemName")
    Item getItemByName(@Param("itemName") String itemName);

    @Query("select i from Item i where i.isActive = :typeStatus")
    List<Item> getItemListByTypeStatus(@Param("typeStatus") Boolean typeStatus);

    @Query("select i from Item i")
    List<Item> getItemList();

    @Query("select i from Item i where i.id =:itemId")
    Item getItemById(@Param("itemId") Long itemId);

}
