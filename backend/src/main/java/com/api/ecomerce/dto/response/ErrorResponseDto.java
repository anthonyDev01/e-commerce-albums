package com.api.ecomerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private LocalDateTime date;
    private String message;
    private Integer code;

    public ErrorResponseDto(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.date = LocalDateTime.now();
    }
}
