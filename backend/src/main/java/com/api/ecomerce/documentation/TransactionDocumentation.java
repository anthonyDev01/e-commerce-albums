package com.api.ecomerce.documentation;

import com.api.ecomerce.dto.response.*;
import com.api.ecomerce.infra.exception.WalletNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface TransactionDocumentation {
    @Operation(summary = "Get metrics from all transactions")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtain metrics successfully",
                            content = {@Content(schema = @Schema(implementation = TransactionMetricsResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

            }
    )
    public ResponseEntity<TransactionMetricsResponseDto> getTransactionMetrics();

    @Operation(summary = "Get metrics transactions from user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtain metrics successfully",
                            content = {@Content(schema = @Schema(implementation = TransactionUserMetricsResponse.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<TransactionUserMetricsResponse> getTransactionMetricsByUser();

    @Operation(summary = "Get metrics transactions from album")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtain metrics successfully",
                            content = {@Content(schema = @Schema(implementation = TransactionAlbumResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

            }
    )
    public ResponseEntity<TransactionAlbumResponseDto> getTransactionMetricsByAlbum();
}
