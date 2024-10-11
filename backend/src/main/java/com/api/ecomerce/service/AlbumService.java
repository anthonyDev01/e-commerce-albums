package com.api.ecomerce.service;
import com.api.ecomerce.dto.request.AlbumRequestDto;
import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
import com.api.ecomerce.dto.response.ArtistSimplifiedDto;
import com.api.ecomerce.infra.exception.*;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.model.User;
import com.api.ecomerce.repository.AlbumRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final AuthService authService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Album buyAlbum(AlbumRequestDto request) throws BalanceIsInsufficientException, AlbumAlreadyPurchasedException, UserNotFoundException, WalletNotFoundException, JsonProcessingException {
        log.info("Attempting buy a album with idSpotify: " + request.getAlbum().getId());

        User user = userService.findUserById(request.getUser_id());
        checkIfIdSpotifyAlreadyPurchasedByUser(request.getAlbum().getId(), user.getId());

        Album album = Album.builder()
                .name(request.getAlbum().getName())
                .idSpotify(request.getAlbum().getId())
                .artistName(convertToJson(request.getAlbum().getArtists()))
                .imageUrl(convertToJson(request.getAlbum().getImages()))
                .spotifyUrl(convertToJson(request.getAlbum().getExternalUrls()))
                .imageUrl(objectMapper.writeValueAsString(request.getAlbum().getImages()))
                .spotifyUrl(objectMapper.writeValueAsString(request.getAlbum().getExternalUrls()))
                .value(BigDecimal.valueOf(Math.random() * ((100.00 - 12.00) + 1) + 12.00).setScale(2, RoundingMode.UP))
                .build();

        checkIfBalanceIsSufficient(user.getId(), album.getValue());


        album.setUser(user);

        this.walletService.deductCreditThePurchase(user, album.getValue());

        log.info("Album with id {} purchased successfully", album.getId());
        this.albumRepository.save(album);
        Transaction transaction = this.transactionService.createTransaction(album, user);

        album.setTransaction(transaction);

        walletService.updatePoints(transaction);

        return this.albumRepository.save(album);
    }

    public List<Album> getAllAlbumsFromUserCollection(UUID id) throws UserNotFoundException {
        log.info("Fetching all albums from user with id: " + id);
        return this.albumRepository.findAllByUserId(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public UUID deleteAlbumById(UUID id) throws AlbumNotFoundException {
        log.info("Attempting to remove album with id: " + id);
        Album album = this.findAlbumById(id);
        album.setDeletedAt(LocalDateTime.now());
        albumRepository.save(album);
        log.info("Album with id {} was removed successfully: ", id);
        return album.getId();
    }

    private Album findAlbumById(UUID id) throws AlbumNotFoundException {
        log.info("Fetching album with id:"  + id);
        return this.albumRepository.findById(id).orElseThrow(() -> {
            log.error("album not found");
            return new AlbumNotFoundException("Album not found!");
        });
    }

    private void checkIfIdSpotifyAlreadyPurchasedByUser(String idSpotify, UUID userId) throws AlbumAlreadyPurchasedException {
        Optional<Album> album = this.albumRepository.findByIdSpotifyAndUserId(idSpotify, userId);
        if (album.isPresent()){
            log.error("This album with id: {} has already been purchased", album.get().getId());
            throw new AlbumAlreadyPurchasedException("This album has already been purchased");
        }
    }
    private void checkIfBalanceIsSufficient(UUID userId, BigDecimal value) throws BalanceIsInsufficientException, UserNotFoundException {
        User user = this.userService.findUserById(userId);
        if (user.getWallet().getBalance().compareTo(value) < 0){
            log.error("The balance of the user with id {} is insufficient", userId);
            throw new BalanceIsInsufficientException("The balance is insufficient");
        }
    }

    private String convertToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }



}
