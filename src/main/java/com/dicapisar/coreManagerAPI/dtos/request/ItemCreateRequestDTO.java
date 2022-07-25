package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCreateRequestDTO {
    private String name;
    private float price;
    private Long brand;
    private Long typeItem;
}
