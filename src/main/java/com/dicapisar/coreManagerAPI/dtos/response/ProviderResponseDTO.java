package com.dicapisar.coreManagerAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderResponseDTO {
    private Long id;
    private boolean isActive;
    private String name;
    private String address;
    private String documentNumber;
    private String email;
    private String phoneNumber;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long creator;
    private Long updater;
}
