package com.api.ecomerce.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = true)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "idSpotify", nullable = false)
    private String idSpotify;

    @Column(name = "artistName", nullable = false, columnDefinition = "TEXT")
    private String artistName;

    @Column(name = "imageUrl", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "spotifyUrl", nullable = false, columnDefinition = "TEXT")
    private String spotifyUrl;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "album")
    private Transaction transaction;
}
