package com.api.ecomerce.controller;

import com.api.ecomerce.dto.response.AlbumsSpotifyResponseDto;
import com.api.ecomerce.dto.response.TransactionMetricsResponseDto;
import com.api.ecomerce.dto.response.TransactionUserMetricsResponse;
import com.api.ecomerce.dto.response.WalletDetailsResponseDto;
import com.api.ecomerce.infra.security.TokenService;
import com.api.ecomerce.service.TransactionService;
import com.api.ecomerce.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionMetrics() {
        TransactionMetricsResponseDto transactionMetricsResponse = TransactionMetricsResponseDto.builder()
                        .total_sales_of_the_day(new BigDecimal(10))
                        .total_sales(9)
                        .average_points_earned(new BigDecimal(47.87))
                        .average_sales_value(new BigDecimal(8))
                        .points_earned_of_the_day(54)
                        .build();


        when(transactionService.findTransactionMetrics()).thenReturn(transactionMetricsResponse);

        ResponseEntity<TransactionMetricsResponseDto> response = transactionController.getTransactionMetrics();

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void getTransactionMetricsByUser() {
        TransactionUserMetricsResponse transactionMetricsResponse = TransactionUserMetricsResponse.builder()
                .total_purchases_of_the_day(new BigDecimal(10))
                .total_purchases(9)
                .average_points_earned(new BigDecimal(47.87))
                .average_purchases_value(new BigDecimal(8))
                .points_earned_of_the_day(54)
                .build();

        when(transactionService.findTransactionMetricsByUser()).thenReturn(transactionMetricsResponse);

        ResponseEntity<TransactionUserMetricsResponse> response = transactionController.getTransactionMetricsByUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}