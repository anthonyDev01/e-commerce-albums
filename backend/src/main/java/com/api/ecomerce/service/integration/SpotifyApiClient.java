package com.api.ecomerce.service.integration;
import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import se.michaelthelin.spotify.SpotifyApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.BadRequestException;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
public class SpotifyApiClient {
    @Value("${spotify.client-id}")
    private String clientId = "72aa391c620d45c29e0590a1bcb679be";

    @Value("${spotify.client-secret}")
    private String clientSecret = "c685961b3b564ad6a25e6a8ce3010d80";

    private SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    public List<AlbumsSpotifyResponseDto> getAlbums(String search) {
        try {

            log.info("client id: " + clientId);
            log.info("client secret: " + clientSecret);
            spotifyApi.setAccessToken(getAccessToken());
            var albums = spotifyApi.searchAlbums(search).market(CountryCode.BR).limit(30).build()
                    .execute().getItems();
            List<AlbumsSpotifyResponseDto> albumDtos = new java.util.ArrayList<>(List.of());
            for (var album : albums) {
                albumDtos.add(AlbumsSpotifyResponseDto.builder()
                        .albumType(album.getAlbumType())
                        .artists(album.getArtists())
                        .externalUrls(album.getExternalUrls())
                        .id(album.getId())
                        .images(album.getImages())
                        .name(album.getName())
                        .releaseDate(album.getReleaseDate())
                        .type(album.getType())
                        .value(BigDecimal.valueOf(Math.random() * ((100.00 - 12.00) + 1) + 12.00).setScale(2, RoundingMode.UP))
                        .build());
            }
            log.info("Albums from Spotify retrieved successfully");
            return albumDtos;
        } catch (IOException | SpotifyWebApiException | ParseException ex) {
            log.error("Error while trying to get albums from Spotify", ex);
            throw new RuntimeException(ex);
        }

    }

    private String getAccessToken() throws IOException, ParseException, SpotifyWebApiException {
        ClientCredentialsRequest clientCredentialsRequest = this.spotifyApi.clientCredentials().build();
        return clientCredentialsRequest.execute().getAccessToken();
    }
}
