package com.api.ecomerce.dto.request;

import com.api.ecomerce.model.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumRequestDto {
    @NotBlank(message = "The 'name' field must not be blank")
    private String name;
    @NotBlank(message = "The 'idSpotify' field must not be blank")
    private String idSpotify;
    @NotBlank(message = "The 'artistName' field must not be blank")
    private String artistName;
    @NotBlank(message = "The 'imageUrl' field must not be blank")
    private String imageUrl;
    @NotBlank(message = "The 'spotifyUrl' field must not be blank")
    private String spotifyUrl;

}
