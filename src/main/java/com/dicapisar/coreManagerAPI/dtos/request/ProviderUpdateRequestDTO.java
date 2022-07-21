package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderUpdateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotEmpty(message = "The attribute 'address' must not be empty")
    private String address;

    @NotEmpty(message = "The attribute 'documentNumber' must not be empty")
    private String documentNumber;

    @NotEmpty(message = "The attribute 'email' must not be empty")
    private String email;

    @NotEmpty(message = "The attribute 'phoneNumber' must not be empty")
    private String phoneNumber;
}
