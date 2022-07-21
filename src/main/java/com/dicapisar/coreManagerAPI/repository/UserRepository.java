package com.dicapisar.coreManagerAPI.repository;

import com.dicapisar.coreManagerAPI.models.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository <User, Long> {
    @Query("select u from User u where u.id =:userId")
    User getUserById(@Param("userId") Long userId);
}
