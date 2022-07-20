package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeItemUpdateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotNull(message = "The attribute 'isPerishable' must not be empty")
    @Column(name = "isPerishable", nullable = false)
    private Boolean isPerishable;
}
