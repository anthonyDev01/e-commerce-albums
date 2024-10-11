package com.api.ecomerce.mapper;
import com.api.ecomerce.dto.response.*;
import com.api.ecomerce.model.Album;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperAlbum {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static AlbumResponseDto toDto(Album album) throws JsonProcessingException {
        return AlbumResponseDto.builder()
                .id(album.getId())
                .name(album.getName())
                .artistName(mapArtists(album.getArtistName()))
                .images(mapImage(album.getImageUrl()))
                .spotifyUrl(mapExternalUrl(album.getSpotifyUrl()))
                .build();
    }

    public static List<AlbumResponseDto> fromAlbumList(List<Album> albums) {
        return albums.stream()
                .map(album -> {
                    try {
                        return new AlbumResponseDto(album);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error converting album to DTO", e);
                    }
                })
                .collect(Collectors.toList());
    }


    private static List<ArtistSimplifiedDto> mapArtists(String artistsJson) {
        try {
            ArtistSimplified[] artists = objectMapper.readValue(artistsJson, ArtistSimplified[].class);
            return Arrays.stream(artists)
                    .map(artist -> new ArtistSimplifiedDto(artist.getHref(), artist.getId(), artist.getName() , artist.getType().getType(), artist.getUri()))
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing artistName JSON", e);
        }
    }

    private static List<ImageDto> mapImage(String imageJson) throws JsonProcessingException {
        try {
            Image[] images = objectMapper.readValue(imageJson, Image[].class);
            return Arrays.stream(images)
                    .map(image -> new ImageDto(image.getUrl(), image.getHeight(), image.getWidth()))
                    .collect(Collectors.toList());
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error processing images JSON", e);
        }
    }

    private static ExternalUrlsWrapperDto mapExternalUrl(String externalUrlJson) {
        try {
            return objectMapper.readValue(externalUrlJson, ExternalUrlsWrapperDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar o JSON de spotifyUrl", e);
        }
    }


}
