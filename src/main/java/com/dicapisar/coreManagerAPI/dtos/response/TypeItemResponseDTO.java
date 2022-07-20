package com.dicapisar.coreManagerAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeItemResponseDTO {
    private Long id;
    private boolean isActive;
    private String name;
    private boolean isPerishable;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long creator;
    private Long updater;
}
