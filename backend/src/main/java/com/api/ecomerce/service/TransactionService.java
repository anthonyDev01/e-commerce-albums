package com.api.ecomerce.service;

import com.api.ecomerce.dto.response.TransactionAlbumResponseDto;
import com.api.ecomerce.dto.response.TransactionMetricsResponseDto;
import com.api.ecomerce.dto.response.TransactionUserMetricsResponse;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.model.User;
import com.api.ecomerce.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AuthService authService;
    public Transaction createTransaction(Album album, User user){
        log.info("Attempting to create a transaction");
        Transaction transaction = Transaction.builder()
                .value(album.getValue())
                .createdAt(LocalDateTime.now())
                .pointsEarned(CalculatedDailyPoints())
                .album(album)
                .user(user)
                .build();

        log.info("Transaction created successfully");
        return this.transactionRepository.save(transaction);
    }


    public TransactionMetricsResponseDto findTransactionMetrics(){

        return TransactionMetricsResponseDto.builder()
                .total_sales_of_the_day(calculateSalesOfTheDay())
                .total_sales(getTotalSales())
                .average_sales_value(getAverageSalesValue())
                .total_points_given(getTotalPoints())
                .average_points_earned(getAveragePointsEarned())
                .points_earned_of_the_day(calculateTotalPointsEarnedOfTheDay())
                .build();
    }

    public TransactionUserMetricsResponse findTransactionMetricsByUser(){
        return TransactionUserMetricsResponse.builder()
                .total_purchases_of_the_day(getTotalPurchasesOfTheDayByUser())
                .total_purchases(getTotalPurchasesByUser())
                .average_purchases_value(getAveragePurchasesByUser())
                .total_points_earned(getTotalPointsByUser())
                .average_points_earned(calculateAveragePointsEarnedByUser())
                .points_earned_of_the_day(calculateTotalPointsEarnedOfTheDayByUser())
                .build();
    }

    public TransactionAlbumResponseDto findTransactionMetricsByAlbum(){
        return TransactionAlbumResponseDto.builder()
                .name_best_selling_album(getBestSellingAlbum())
                .name_most_Expensive_album(mostExpensiveAlbum())
                .name_artists_with_most_albums(artistsWithMostAlbums())
                .build();
    }

    private BigDecimal calculateSalesOfTheDay(){
        log.info("Attempting to calculate total sales of the day");
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        log.info("Calculate total sales of the day successfully");
        return this.transactionRepository.calculateTotalSalesOfTheDay(startOfDay, endOfDay);
    }

    private Integer getTotalSales(){
        log.info("Attempting to calculate total sales");
        return this.transactionRepository.totalSales();
    }

    private BigDecimal getAverageSalesValue(){
        log.info("Attempting to calculate total average sales");
        return this.transactionRepository.calculeteAverageSalesValue().setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getAveragePointsEarned(){
        log.info("Attempting to calculate average points earned");
        return this.transactionRepository.calculeteAveragePointsEarned().setScale(2, RoundingMode.HALF_EVEN);
    }

    private Integer getTotalPoints(){
        log.info("Attempting to calculate total points earned");
        return this.transactionRepository.calculateTotalPointsEarned();
    }

    private Integer calculateTotalPointsEarnedOfTheDay(){
        log.info("Attempting to calculate total points earned of the day");
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        log.info("Calculated total points earned of the day successfully");
        return this.transactionRepository.calculateTotalPointsEarnedOfTheDay(startOfDay, endOfDay);
    }

    // metodos por id

    private BigDecimal getTotalPurchasesOfTheDayByUser(){
        log.info("Attempting to calculate total purchases of the day from user");
        User user = authService.getAuthenticatedUser();
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        log.info("Calculated total purchases from user of the day successfully");
        return transactionRepository.calculateTotalPurchasesOfTheDayByUser(user.getId(), startOfDay, endOfDay);
    }

    private Integer getTotalPurchasesByUser(){
        log.info("Attempting to calculate total purchases from user");
        User user = authService.getAuthenticatedUser();
        log.info("Calculated total purchases from user successfully");
        return this.transactionRepository.calculateTotalPurchasesByUser(user.getId());
    }

    private BigDecimal getAveragePurchasesByUser(){
        log.info("Attempting to calculate average purchases from user");
        User user = authService.getAuthenticatedUser();
        log.info("Calculated average purchases from user successfully");
        return this.transactionRepository.calculateAveragePurchasesByUser(user.getId());
    }

    private BigDecimal calculateAveragePointsEarnedByUser(){
        log.info("Attempting to calculate average points earned from user");
        User user = authService.getAuthenticatedUser();
        log.info("Calculated average points earned from user successfully");
        return this.transactionRepository.calculateAveragePointsEarnedByUser(user.getId());
    }

    private Integer getTotalPointsByUser(){
        log.info("Attempting to calculate total points earned from user");
        User user = authService.getAuthenticatedUser();
        log.info("Calculated total points earned from user successfully");
        return this.transactionRepository.calculateTotalPointsEarnedByUser(user.getId());
    }

    private Integer calculateTotalPointsEarnedOfTheDayByUser(){
        log.info("Attempting to calculate total points earned of the day from user");
        User user = authService.getAuthenticatedUser();
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        log.info("Calculated total points earned of the day from user successfully");
        return this.transactionRepository.calculateTotalPointsEarnedOfTheDayByUser(user.getId(), startOfDay, endOfDay);
    }

    //metricas por album

    private String getBestSellingAlbum() {
        log.info("Attempting to get best selling album");
        List<String> result = transactionRepository.findBesSellingAlbum();
        if (!result.isEmpty()) {
            log.info("Obtain best selling album successfully");
            return result.get(0);
        }
        return null;
    }

    private String mostExpensiveAlbum(){
        log.info("Attempting to get most expensive album");
        return transactionRepository.findMostExpensiveAlbum();
    }

    private String artistsWithMostAlbums(){
        log.info("Attempting to get artist with most albums");
        List<String> result = transactionRepository.findArtistsWithMostAlbums();
        if (!result.isEmpty()) {
            log.info("Obtain artist with most albums successfully");
            return result.get(0);
        }
        return null;
    }

    private Integer CalculatedDailyPoints(){
        log.info("Attempting to get point of the day");
        DayOfWeek day = LocalDateTime.now().getDayOfWeek();
        return switch (day){
            case SUNDAY -> 25;
            case MONDAY -> 7;
            case TUESDAY -> 6;
            case WEDNESDAY -> 2;
            case THURSDAY -> 10;
            case FRIDAY -> 15;
            case SATURDAY -> 20;
        };
    }
}
