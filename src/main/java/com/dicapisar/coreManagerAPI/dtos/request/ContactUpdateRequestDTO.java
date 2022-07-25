package com.dicapisar.coreManagerAPI.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactUpdateRequestDTO {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;

    @NotEmpty(message = "The attribute 'phoneNumber' must not be empty")
    private String phoneNumber;

    @NotEmpty(message = "The attribute 'email' must not be empty")
    private String email;
}
