package com.dicapisar.coreManagerAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactResponseDTO {
    private Long id;
    private boolean isActive;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long provider;
    private Long creator;
    private Long updater;
}
