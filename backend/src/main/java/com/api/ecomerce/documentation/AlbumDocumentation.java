package com.api.ecomerce.documentation;

import com.api.ecomerce.dto.request.AlbumRequestDto;
import com.api.ecomerce.dto.request.LoginUserRequestDto;
import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.UpdateUserRequestDto;
import com.api.ecomerce.dto.response.*;
import com.api.ecomerce.infra.exception.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.exceptions.detailed.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface AlbumDocumentation {
    @Operation(summary = "Get all albums from Spotify service by Text parameter")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search all successfully searched albums",
                            content = {@Content(schema = @Schema(implementation = AlbumsSpotifyResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = BadRequestException.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<List<AlbumsSpotifyResponseDto>> getAllAlbums(@RequestParam("search") String search);

    @Operation(summary = "Search all albums from my collections")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "all user albums found successfully",
                            content = {@Content(schema = @Schema(implementation = AlbumResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),


                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Album not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<List<AlbumResponseDto>> getAllAlbumFromUserCollection(@PathVariable UUID userId) throws UserNotFoundException, AlbumNotFoundException;


    @Operation(summary = "Buy a album")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "album purchased successfully",
                            content = {@Content(schema = @Schema(implementation = AlbumResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "401",
                            description = "Balance user is insufficient",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Wallet not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "409",
                            description = "Album Already Purchased",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

            }
    )
    public ResponseEntity<AlbumResponseDto> buyAlbum(@Valid @RequestBody AlbumRequestDto body) throws BalanceIsInsufficientException, AlbumAlreadyPurchasedException, UserNotFoundException, WalletNotFoundException, JsonProcessingException;


    @Operation(summary = "Delete album from my collection")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "album deleted successfully",
                            content = {@Content(schema = @Schema(implementation = UUID.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Album not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
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
    public ResponseEntity<UUID> deleteAlbum(@PathVariable UUID id) throws AlbumNotFoundException;




}
