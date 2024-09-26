package com.api.ecomerce.dto.response;

import com.api.ecomerce.model.Wallet;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDetailsResponseDto {
    private UUID wallet_id;
    private BigDecimal balance;
    private Integer points;
    private LocalDateTime lastUpdate;
    private UUID user_id;

    public WalletDetailsResponseDto(Wallet wallet) {
        this.wallet_id = wallet.getId();
        this.balance = wallet.getBalance();
        this.points = wallet.getPoints();
        this.lastUpdate = wallet.getLastUpdate();
        this.user_id = wallet.getUser().getId();
    }
}
