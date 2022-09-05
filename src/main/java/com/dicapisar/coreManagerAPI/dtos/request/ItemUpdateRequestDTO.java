package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemUpdateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotNull(message = "The attribute 'price' must not be empty")
    private float price;

    @NotNull(message = "The attribute 'brand' must not be empty")
    private Long brand;

    @NotNull(message = "The attribute 'typeItem' must not be empty")
    private Long typeItem;
}
