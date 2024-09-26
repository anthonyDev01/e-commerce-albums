package com.api.ecomerce.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionAlbumResponseDto {
    private String name_best_selling_album;
    private String name_most_Expensive_album;
    private String name_artists_with_most_albums;

}
