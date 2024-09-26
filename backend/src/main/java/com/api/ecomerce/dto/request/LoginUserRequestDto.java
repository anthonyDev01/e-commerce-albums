package com.api.ecomerce.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDto {
    @NotBlank
    @Email(message = "Must be a well-formed email address")
    private String email;

    @NotBlank(message = "The 'password' field must not be blank")
    @Size(min = 8, message = "The 'password' field must be at least 8 characters long")
    private String password;


}
