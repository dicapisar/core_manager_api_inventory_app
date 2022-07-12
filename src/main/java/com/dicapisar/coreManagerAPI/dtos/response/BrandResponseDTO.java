package com.dicapisar.coreManagerAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandResponseDTO {
    private Long id;
    private boolean isActive;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long creator;
    private Long updater;
}
