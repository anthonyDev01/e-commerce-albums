package com.api.ecomerce.documentation;

import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.WalletAddBalanceRequestDto;
import com.api.ecomerce.dto.response.*;
import com.api.ecomerce.infra.exception.WalletNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface WalletDocumentation {
    @Operation(summary = "Obtain user wallet")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtain a wallet successfully",
                            content = {@Content(schema = @Schema(implementation = WalletDetailsResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Wallet not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<WalletDetailsResponseDto> getWalletById(@PathVariable UUID walletId) throws WalletNotFoundException;

    @Operation(summary = "Add credit to the user wallet")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "add credit to the user wallet successfully",
                            content = {@Content(schema = @Schema(implementation = WalletBalanceResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<WalletBalanceResponseDto> addCreditToWallet(@Valid @RequestBody WalletAddBalanceRequestDto body);

}
