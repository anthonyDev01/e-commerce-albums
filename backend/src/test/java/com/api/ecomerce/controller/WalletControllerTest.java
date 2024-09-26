package com.api.ecomerce.controller;

import com.api.ecomerce.dto.request.WalletAddBalanceRequestDto;
import com.api.ecomerce.dto.response.UserResponseDto;
import com.api.ecomerce.dto.response.WalletBalanceResponseDto;
import com.api.ecomerce.dto.response.WalletDetailsResponseDto;
import com.api.ecomerce.infra.exception.WalletNotFoundException;
import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.service.AlbumService;
import com.api.ecomerce.service.WalletService;
import com.api.ecomerce.service.integration.SpotifyApiClient;
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

class WalletControllerTest {
    @Mock
    private WalletService walletService;


    @InjectMocks
    private WalletController walletController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWalletById() throws WalletNotFoundException {
        UUID id = UUID.randomUUID();

        User user = User.builder()
                .id(UUID.randomUUID())
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();

        Wallet wallet = Wallet.builder()
                        .id(id)
                        .balance(new BigDecimal(100))
                        .lastUpdate(LocalDateTime.now())
                        .points(0)
                        .user(user)
                        .build();

        user.setWallet(wallet);

        when(walletService.findWalletById(id)).thenReturn(wallet);

        ResponseEntity<WalletDetailsResponseDto> response = walletController.getWalletById(wallet.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user.getWallet().getId(), response.getBody().getWallet_id());
    }

    @Test
    void addCreditToWallet() {
        UUID id = UUID.randomUUID();

        User user = User.builder()
                .id(UUID.randomUUID())
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();

        Wallet wallet = Wallet.builder()
                .id(id)
                .balance(new BigDecimal(100))
                .lastUpdate(LocalDateTime.now())
                .points(0)
                .user(user)
                .build();

        WalletAddBalanceRequestDto walletAddBalanceRequestDto = new WalletAddBalanceRequestDto(new BigDecimal(200));


        when(walletService.addBalance(walletAddBalanceRequestDto)).thenReturn(wallet);

        ResponseEntity<WalletBalanceResponseDto> response = walletController.addCreditToWallet(walletAddBalanceRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}