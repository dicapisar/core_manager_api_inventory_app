package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.Brand;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("select b from Brand b where b.id =:brandId and b.isActive = true")
    Brand getBrandByIdAAndIsActive(@Param("brandId") Long brandId);
}
