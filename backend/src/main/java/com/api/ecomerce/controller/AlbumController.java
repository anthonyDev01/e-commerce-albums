package com.api.ecomerce.controller;

import com.api.ecomerce.documentation.AlbumDocumentation;
import com.api.ecomerce.dto.request.AlbumRequestDto;
import com.api.ecomerce.dto.response.AlbumResponseDto;
import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
import com.api.ecomerce.infra.exception.*;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.service.AlbumService;
import com.api.ecomerce.service.integration.SpotifyApiClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController implements AlbumDocumentation {
    private final SpotifyApiClient albumApiService;
    private final AlbumService albumService;

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<AlbumsSpotifyResponseDto>> getAllAlbums(@RequestParam("search") String search) {
        return ResponseEntity.ok(albumApiService.getAlbums(search));
    }

    @Override
    @GetMapping("/my-collection/{userId}")
    public ResponseEntity<List<AlbumResponseDto>> getAllAlbumFromUserCollection(@PathVariable UUID userId) throws UserNotFoundException, AlbumNotFoundException {
        List<Album> albums = albumService.getAllAlbumsFromUserCollection(userId);
        List<AlbumResponseDto> albumResponseDtos = new AlbumResponseDto().fromAlbumList(albums);
        return ResponseEntity.ok(albumResponseDtos);
    }

    @Override
    @PostMapping("/sale")
    public ResponseEntity<AlbumResponseDto> buyAlbum(@Valid @RequestBody AlbumRequestDto body) throws BalanceIsInsufficientException, AlbumAlreadyPurchasedException, UserNotFoundException, WalletNotFoundException {
        Album album = this.albumService.buyAlbum(body);
        return ResponseEntity.ok(new AlbumResponseDto(album));
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<UUID> deleteAlbum(@PathVariable UUID id) throws AlbumNotFoundException {
        UUID album = this.albumService.deleteAlbumById(id);
        return ResponseEntity.ok(album);
    }
}
