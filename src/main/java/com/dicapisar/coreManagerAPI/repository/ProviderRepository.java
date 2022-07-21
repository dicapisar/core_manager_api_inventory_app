package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.Provider;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("select p from Provider p where p.id = :providerId and p.isActive = true")
    Provider getProviderByIdAAndIsActive(@Param("providerId") Long providerId);

    @Query("select p from Provider p where p.name = :providerName")
    Provider getProviderByName(@Param("providerName") String providerName);

    @Query("select p from Provider p where p.isActive = :typeStatus")
    List<Provider> getProviderListByTypeStatus(@Param("typeStatus") Boolean typeStatus);

    @Query("select p from Brand p")
    List<Provider> getProviderList();

    @Query("select p from Provider p where p.id = :providerId")
    Provider getProviderById(@Param("providerId") Long providerId);

}
