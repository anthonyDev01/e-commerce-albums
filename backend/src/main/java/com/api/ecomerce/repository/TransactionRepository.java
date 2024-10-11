package com.api.ecomerce.repository;

import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByUserId(UUID userId);

    @Query("SELECT COALESCE(SUM(t.value), 0) From Transaction t WHERE t.createdAt BETWEEN :startOfDay AND :endOfDay")
    BigDecimal calculateTotalSalesOfTheDay(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT COALESCE(COUNT(t), 0) FROM Transaction t")
    Integer totalSales();

    @Query("SELECT COALESCE(AVG(t.value), 0) FROM Transaction t")
    BigDecimal calculeteAverageSalesValue();

    @Query("SELECT COALESCE(SUM(t.pointsEarned), 0) FROM Transaction t")
    Integer calculateTotalPointsEarned();

    @Query("SELECT COALESCE(AVG(t.pointsEarned), 0) FROM Transaction t")
    BigDecimal calculeteAveragePointsEarned();

    @Query("SELECT COALESCE(SUM(t.pointsEarned) ,0) FROM Transaction t WHERE t.createdAt BETWEEN :startOfDay AND :endOfDay")
    Integer calculateTotalPointsEarnedOfTheDay(LocalDateTime startOfDay, LocalDateTime endOfDay);

    //<===================consultas por id ===================>

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t WHERE t.user.id = :id AND t.createdAt BETWEEN :startOfDay AND :endOfDay")
    BigDecimal calculateTotalPurchasesOfTheDayByUser(UUID id,LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t WHERE t.user.id = :id")
    Integer calculateTotalPurchasesByUser(UUID id);

    @Query("SELECT COALESCE(AVG(t.value), 0) FROM Transaction t WHERE t.user.id = :id")
    BigDecimal calculateAveragePurchasesByUser(UUID id);

    @Query("SELECT COALESCE(SUM(t.pointsEarned), 0) FROM Transaction t WHERE t.user.id = :id")
    Integer calculateTotalPointsEarnedByUser(UUID id);

    @Query("SELECT COALESCE(AVG(t.pointsEarned), 0) FROM Transaction t WHERE t.user.id = :id")
    BigDecimal calculateAveragePointsEarnedByUser(UUID id);

    @Query("SELECT COALESCE(SUM(t.pointsEarned) ,0) FROM Transaction t WHERE t.user.id = :id AND t.createdAt BETWEEN :startOfDay AND :endOfDay")
    Integer calculateTotalPointsEarnedOfTheDayByUser(UUID id,LocalDateTime startOfDay, LocalDateTime endOfDay);


    //album

    @Query("SELECT t.album.name " +
            "FROM Transaction t " +
            "GROUP BY t.album.name " +
            "ORDER BY COUNT(t.album.idSpotify) DESC")
    List<String> findBesSellingAlbum();

    @Query("SELECT a.name " +
            "FROM Album a " +
            "WHERE a.value = (SELECT MAX(t.album.value) FROM Transaction t)")
    String findMostExpensiveAlbum();

    @Query("SELECT t.album.artistName " +
            "FROM Transaction t " +
            "GROUP BY t.album.artistName " +
            "ORDER BY COUNT(DISTINCT t.album.idSpotify) DESC")
    List<String> findArtistsWithMostAlbums();



}
