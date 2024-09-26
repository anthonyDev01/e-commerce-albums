package com.api.ecomerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataErrorValidationDto {
    private LocalDateTime date;
    private String field;
    private String message;
    private Integer code;

    public DataErrorValidationDto(FieldError error){
        this(LocalDateTime.now(), error.getField(), error.getDefaultMessage(), 400);
    }
}
