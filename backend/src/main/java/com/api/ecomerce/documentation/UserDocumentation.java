package com.api.ecomerce.documentation;

import com.api.ecomerce.dto.request.LoginUserRequestDto;
import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.UpdateUserRequestDto;
import com.api.ecomerce.dto.response.*;
import com.api.ecomerce.infra.exception.InvalidCredentialException;
import com.api.ecomerce.infra.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserDocumentation {
    @Operation(summary = "Create a new user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "user singUp successfully",
                            content = {@Content(schema = @Schema(implementation = SignUpResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "409",
                            description = "User email is already in use",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),
            }
    )
    public ResponseEntity<SignUpResponseDto> singUpUser(@Valid @RequestBody SignUpRequestDto body);

    @Operation(summary = "Authenticate a user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "user authenticated successfully",
                            content = {@Content(schema = @Schema(implementation = LoginResponseDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "An error occurred during the process",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid credentials",
                            content = {@Content(schema = @Schema(implementation = DataErrorValidationDto.class))}
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),


            }
    )
    public ResponseEntity<LoginResponseDto> authUser(@Valid @RequestBody LoginUserRequestDto body) throws UserNotFoundException, InvalidCredentialException;


    @Operation(summary = "List users")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "search all successfully searched users",
                            content = {@Content(schema = @Schema(implementation = UserResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT token has expired",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}
                    ),

            }
    )
    public ResponseEntity<List<UserResponseDto>> getAllUsers();


    @Operation(summary = "Get a user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "search all successfully searched users",
                            content = {@Content(schema = @Schema(implementation = UserResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
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
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) throws UserNotFoundException;

    @Operation(summary = "Update user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User updated successfully",
                            content = {@Content(schema = @Schema(implementation = UpdateUserResponseDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
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
    public ResponseEntity<UpdateUserResponseDto> updateUser(@Valid @RequestBody UpdateUserRequestDto body);
}
