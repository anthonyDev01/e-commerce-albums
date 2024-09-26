package com.api.ecomerce.dto.response;

import com.api.ecomerce.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletBalanceResponseDto {
    private UUID wallet_id;
    private BigDecimal balance;

    public WalletBalanceResponseDto(Wallet wallet) {
        this.wallet_id = wallet.getId();
        this.balance = wallet.getBalance();
    }
}
