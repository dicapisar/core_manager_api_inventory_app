package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeItemCreateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotNull(message = "The attribute 'isPerishable' must not be empty")
    @Column(name = "isPerishable", nullable = false)
    private Boolean isPerishable;
}
