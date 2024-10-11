package com.api.ecomerce.dto.request;

import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
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

    private AlbumsSpotifyResponseDto album;
    private UUID user_id;

}
