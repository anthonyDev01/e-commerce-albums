package com.api.ecomerce.dto.response;

import com.api.ecomerce.model.Album;
import lombok.*;

import java.math.BigDecimal;
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
    private String artistName;
    private String imageUrl;
    private String spotifyUrl;
    private BigDecimal value;

    public AlbumResponseDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.idSpotify = album.getIdSpotify();
        this.artistName = album.getArtistName();
        this.imageUrl = album.getImageUrl();
        this.spotifyUrl = album.getSpotifyUrl();
        this.value = album.getValue();
    }

    public static List<AlbumResponseDto> fromAlbumList(List<Album> albums) {
        return albums.stream()
                .map(AlbumResponseDto::new)
                .collect(Collectors.toList());
    }
}
