package com.api.ecomerce.dto.request;

import com.api.ecomerce.model.Wallet;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletAddBalanceRequestDto {
    @NotNull(message = "The 'balance' field must not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Balance must be greater than zero")
    private BigDecimal balance;


    private UUID wallet_id;
}
