package com.api.ecomerce.controller;

import com.api.ecomerce.documentation.WalletDocumentation;
import com.api.ecomerce.dto.request.WalletAddBalanceRequestDto;
import com.api.ecomerce.dto.response.WalletBalanceResponseDto;
import com.api.ecomerce.dto.response.WalletDetailsResponseDto;
import com.api.ecomerce.infra.exception.WalletNotFoundException;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController implements WalletDocumentation {

    private final WalletService walletService;

    @Override
    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDetailsResponseDto> getWalletById(@PathVariable UUID walletId) throws WalletNotFoundException {
        Wallet wallet = this.walletService.findWalletById(walletId);
        return ResponseEntity.ok(new WalletDetailsResponseDto(wallet));
    }

    @Override
    @PostMapping("/credit")
    public ResponseEntity<WalletBalanceResponseDto> addCreditToWallet(@Valid @RequestBody WalletAddBalanceRequestDto body) throws WalletNotFoundException {
        Wallet wallet = this.walletService.addBalance(body);
        return ResponseEntity.ok(new WalletBalanceResponseDto(wallet));
    }
}
