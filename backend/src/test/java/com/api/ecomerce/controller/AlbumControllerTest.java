package com.api.ecomerce.controller;

import com.api.ecomerce.dto.request.AlbumRequestDto;
import com.api.ecomerce.dto.response.AlbumResponseDto;
import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
import com.api.ecomerce.dto.response.UserResponseDto;
import com.api.ecomerce.infra.exception.*;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.service.AlbumService;
import com.api.ecomerce.service.UserService;
import com.api.ecomerce.service.integration.SpotifyApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AlbumControllerTest {
    @Mock
    private AlbumService albumService;

    @Mock
    private SpotifyApiClient albumApiService;

    @InjectMocks
    private AlbumController albumController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return all albuns when everything is ok")
    void getAllAlbums() {
        List<AlbumsSpotifyResponseDto> albumsSpotifyResponse = new ArrayList<>();
        String search = "Kayblack";

        when(albumApiService.getAlbums("kayblack")).thenReturn(albumsSpotifyResponse);

        ResponseEntity<List<AlbumsSpotifyResponseDto>> response = albumController.getAllAlbums(search);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void getAllAlbumFromUserCollection() throws UserNotFoundException, AlbumNotFoundException {
        List<Album> albums = new ArrayList<>();

        albums.add(Album.builder()
                .id(UUID.randomUUID())
                .name("Contradicoes")
                .idSpotify("apskdpaksd")
                .artistName("Kayblack")
                .imageUrl("image.png")
                .spotifyUrl("http//localhost:8080")
                .value(new BigDecimal(90))
                .build()
        );

        when(albumService.getAllAlbumsFromUserCollection(albums.get(0).getId())).thenReturn(albums);

        ResponseEntity<List<AlbumResponseDto>> response = albumController.getAllAlbumFromUserCollection(albums.get(0).getId());

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void buyAlbum() throws UserNotFoundException, BalanceIsInsufficientException, AlbumAlreadyPurchasedException, WalletNotFoundException, AlbumNotFoundException {

        AlbumRequestDto albumRequestDto = AlbumRequestDto.builder()
                .name("Contradicoes")
                .idSpotify("apskdpaksd")
                .artistName("Kayblack")
                .imageUrl("image.png")
                .spotifyUrl("http//localhost:8080")
                .build();

        Album album = Album.builder()
                .id(UUID.randomUUID())
                .name(albumRequestDto.getName())
                .idSpotify(albumRequestDto.getIdSpotify())
                .artistName(albumRequestDto.getArtistName())
                .imageUrl(albumRequestDto.getImageUrl())
                .spotifyUrl(albumRequestDto.getSpotifyUrl())
                .value(new BigDecimal(100))
                .build();


        when(albumService.buyAlbum(albumRequestDto)).thenReturn(album);

        ResponseEntity<AlbumResponseDto> response = albumController.buyAlbum(albumRequestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(album.getId(), response.getBody().getId());


    }

    @Test
    void deleteAlbum() throws AlbumNotFoundException {
        UUID id = UUID.randomUUID();

        when(albumService.deleteAlbumById(id)).thenReturn(id);

        ResponseEntity<UUID> response = albumController.deleteAlbum(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody());

    }


}