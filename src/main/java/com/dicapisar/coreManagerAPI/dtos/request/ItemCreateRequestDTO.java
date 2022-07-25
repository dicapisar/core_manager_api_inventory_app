package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCreateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotEmpty(message = "The attribute 'price' must not be empty")
    private float price;

    @NotEmpty(message = "The attribute 'brand' must not be empty")
    private Long brand;

    @NotEmpty(message = "The attribute 'typeItem' must not be empty")
    private Long typeItem;
}
