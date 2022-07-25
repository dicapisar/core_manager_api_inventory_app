package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.Contact;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c where c.id = :contactId and c.provider.id = :providerId and c.isActive = true")
    Contact getContactByIdAAndIsActiveAndProviderId(@Param("contactId") Long contactId, @Param("providerId") Long providerId);

    @Query("select c from Contact c where c.id = :contactId")
    Contact getContactById(@Param("contactId") Long contactId);

    @Query("select c from Contact c where c.name = :contactName and c.provider.id = :providerId")
    Contact getContactByNameAndProviderId(@Param("contactName") String contactName, @Param("providerId") Long providerId);

    @Query("select c from Contact c where c.isActive = :typeStatus and c.provider.id = :providerId")
    List<Contact> getContactListByTypeStatusAndProviderId(@Param("typeStatus") Boolean typeStatus, @Param("providerId") Long providerId);

    @Query("select c from Contact c where c.provider.id = :providerId")
    List<Contact> getContactListByProviderId(@Param("providerId") Long providerId);

    @Query("select c from Contact c where c.id = :contactId and c.provider.id = :providerId")
    Contact getContactByIdAndProviderId(@Param("contactId") Long contactId, @Param("providerId") Long providerId);

}
