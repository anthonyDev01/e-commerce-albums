package com.api.ecomerce.dto.response;

import com.api.ecomerce.mapper.MapperAlbum;
import com.api.ecomerce.model.Album;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumResponseDto {


    private UUID id;
    private String name;
    private String idSpotify;
    private List<ArtistSimplifiedDto> artistName;
    private List<ImageDto> images;
    private ExternalUrlsWrapperDto spotifyUrl;
    private BigDecimal value;


    public AlbumResponseDto(Album album) throws JsonProcessingException {
        AlbumResponseDto albumMap = MapperAlbum.toDto(album);

        this.id = album.getId();
        this.name = album.getName();
        this.idSpotify = album.getIdSpotify();
        this.artistName = albumMap.getArtistName();
        this.images = albumMap.getImages();
        this.spotifyUrl = albumMap.getSpotifyUrl();
        this.value = album.getValue();
    }
}
