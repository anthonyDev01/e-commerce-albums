package com.api.ecomerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistSimplifiedDto {
    private String href;
    private String id;
    private String name;
    private String type = "ALBUM";
    private String uri;
}
