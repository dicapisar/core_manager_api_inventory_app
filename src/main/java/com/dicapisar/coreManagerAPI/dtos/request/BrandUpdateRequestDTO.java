package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandUpdateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;
}
