package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.TypeItem;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeItemRepository extends JpaRepository<TypeItem, Long> {
    @Query("select ti from TypeItem ti where ti.id = :typeItemId and ti.isActive = true")
    TypeItem getTypeItemByIdAndIsActive(@Param("typeItemId") Long typeItemId);

    @Query("select ti from TypeItem ti where ti.name = :typeItemName")
    TypeItem getTypeItemByName(@Param("typeItemName") String typeItemName);

    @Query("select ti from TypeItem ti where ti.isActive = :typeStatus")
    List<TypeItem> getTypeItemListByTypeStatus(@Param("typeStatus") Boolean typeStatus);

    @Query("select ti from TypeItem ti")
    List<TypeItem> getTypeItemList();

    @Query("select ti from TypeItem ti where ti.id = :typeItemId")
    TypeItem getTypeItemById(@Param("typeItemId") Long typeItemId);

}
