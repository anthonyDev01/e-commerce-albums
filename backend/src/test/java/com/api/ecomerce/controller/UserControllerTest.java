package com.api.ecomerce.controller;

import com.api.ecomerce.dto.request.LoginUserRequestDto;
import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.UpdateUserRequestDto;
import com.api.ecomerce.dto.response.LoginResponseDto;
import com.api.ecomerce.dto.response.SignUpResponseDto;
import com.api.ecomerce.dto.response.UpdateUserResponseDto;
import com.api.ecomerce.dto.response.UserResponseDto;
import com.api.ecomerce.infra.exception.InvalidCredentialException;
import com.api.ecomerce.infra.exception.UserNotFoundException;
import com.api.ecomerce.infra.security.TokenService;
import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create user successfully when everything is ok")
    void singUpUser() {
        SignUpRequestDto request = new SignUpRequestDto("anthony", "anthony@outlook.com", "12345678");
        User user = User.builder().email(request.getEmail()).password(request.getPassword()).build();
        String token = "token";

        when(userService.saveUser(any(SignUpRequestDto.class))).thenReturn(user);
        when(tokenService.generateToken(any(User.class))).thenReturn(token);

        ResponseEntity<SignUpResponseDto> response = userController.singUpUser(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(token, response.getBody().getToken());
    }

    @Test
    @DisplayName("Should authenticate user when everything is ok")
    void authUser() throws UserNotFoundException, InvalidCredentialException {
        LoginUserRequestDto request = new LoginUserRequestDto("anthony@outlook.com", "12345678");
        String token = "token";

        when(userService.auth(any(LoginUserRequestDto.class))).thenReturn(token);

        ResponseEntity<LoginResponseDto> response = userController.authUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());

    }

    @Test
    @DisplayName("Should return all users when everything is ok")
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        User user = User.builder()
                .id(UUID.randomUUID())
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();
        users.add(user);
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal(0), 0, LocalDateTime.now(), user);
        user.setWallet(wallet);

        when(userService.listAllUsers()).thenReturn(users);

        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    @DisplayName("Should return user by id when everything is ok")
    void getUserById() throws UserNotFoundException {

        User user = User.builder()
                .id(UUID.randomUUID())
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();

        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal(0), 0, LocalDateTime.now(), user);
        user.setWallet(wallet);

        when(userService.findUserById(user.getId())).thenReturn(user);

        ResponseEntity<UserResponseDto> response = userController.getUserById(user.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user.getId(), response.getBody().getId());

    }

    @Test
    @DisplayName("Should update user when everything is ok")
    void updateUser() {
        UpdateUserRequestDto request = new UpdateUserRequestDto("teste", "111111111");
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        when(userService.updateUser(request)).thenReturn(user);

        ResponseEntity<UpdateUserResponseDto> response = userController.updateUser(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user.getName(), response.getBody().getName());
        assertEquals(user.getId(), response.getBody().getId());

    }
}