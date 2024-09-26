package com.api.ecomerce.dto.response;

import lombok.*;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumsSpotifyResponseDto {
    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private ExternalUrl externalUrls;
    private String id;
    private Image[] images;
    private String name;
    private String releaseDate;
    private ModelObjectType type;
    private BigDecimal value;
}
