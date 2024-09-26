package com.api.ecomerce.repository;
import com.api.ecomerce.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    @Query("SELECT a FROM Album a where a.idSpotify = ?1 AND a.user.id = ?2 AND a.deletedAt IS NULL")
    Optional<Album> findByIdSpotifyAndUserId(String idSpotify, UUID userId);

    @Query("SELECT a FROM Album a where a.user.id = ?1 AND a.deletedAt IS NULL")
    Optional<List<Album>> findAllByUserId(UUID userId);
}
