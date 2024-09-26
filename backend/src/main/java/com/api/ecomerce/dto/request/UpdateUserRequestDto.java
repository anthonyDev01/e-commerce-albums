package com.api.ecomerce.dto.request;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
    @NotBlank(message = "The 'name' field must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The 'name' field must contain only letters and spaces")
    @Size(min = 2, max = 100, message = "The 'name' field must be between 2 and 100 characters long")
    private String name;

    @NotBlank(message = "The 'password' field must not be blank")
    @Size(min = 8, message = "The 'password' field must be at least 8 characters long")
    private String password;
}
