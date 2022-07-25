package com.dicapisar.coreManagerAPI.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDTO {
    private Long id;
    private boolean isActive;
    private String status;
    private String name;
    private int count;
    private float price;
    private Long brand;
    private Long typeItem;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long creator;
    private Long updater;
}
