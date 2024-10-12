package com.api.ecomerce.dto.response;

import com.api.ecomerce.mapper.MapperAlbum;
import com.api.ecomerce.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private UUID id;
    private BigDecimal value;
    private LocalDateTime created_at;
    private Integer points_earned;
    private String artist_name;
    private String spotify_url;
    private String image_url;
    private LocalDateTime deleted_at;


    public TransactionResponseDto(Transaction transaction) throws JsonProcessingException {
        AlbumResponseDto albumMap = MapperAlbum.toDto(transaction.getAlbum());

        this.id = transaction.getId();
        this.value = transaction.getValue();
        this.created_at = transaction.getCreatedAt();
        this.points_earned = transaction.getPointsEarned();
        this.artist_name = albumMap.getArtistName().get(0).getName();
        this.spotify_url = albumMap.getSpotifyUrl().getExternalUrls().getSpotify();
        this.image_url = albumMap.getImages().get(0).getUrl();
        this.deleted_at = transaction.getAlbum().getDeletedAt();
    }

    public static List<TransactionResponseDto> toListTransactions(List<Transaction> transactions){
        return transactions.stream()
                .map(transaction -> {
                    try {
                         return new TransactionResponseDto(transaction);
                    }catch (JsonProcessingException e) {
                        throw new RuntimeException("Error converting album to DTO", e);
                    }
                })
                .collect(Collectors.toList());
    }
}
