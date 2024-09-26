package com.api.ecomerce.infra.exception;

import com.api.ecomerce.dto.response.DataErrorValidationDto;
import com.api.ecomerce.dto.response.ErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import se.michaelthelin.spotify.exceptions.detailed.BadRequestException;

import java.util.List;

@Log4j2
@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlerUserAlreadyExists(UserAlreadyExistsException exception){
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponseDto> handlerInvalidCredential(InvalidCredentialException exception){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerUserNotFound(UserNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerWalletNotFound(WalletNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(AlbumAlreadyPurchasedException.class)
    public ResponseEntity<ErrorResponseDto> handlerAlbumAlreadyPurchased(AlbumAlreadyPurchasedException exception){
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(BalanceIsInsufficientException.class)
    public ResponseEntity<ErrorResponseDto> handlerBalanceIsInsufficient(BalanceIsInsufficientException exception){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerAlbumNotFound(AlbumNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadable(HttpMessageNotReadableException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestSpotify(BadRequestException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponseDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {

        String errorMessage = "Invalid value for parameter: " + exception.getName() + ". " +
                "Expected type: " + exception.getRequiredType().getSimpleName();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponseDto(errorMessage, status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerFieldNull(MethodArgumentNotValidException exception){
        List<FieldError> errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidationDto::new).toList());
    }

}
