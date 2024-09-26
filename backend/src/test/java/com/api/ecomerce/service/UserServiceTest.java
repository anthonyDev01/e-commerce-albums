package com.api.ecomerce.service;

import com.api.ecomerce.dto.request.LoginUserRequestDto;
import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.UpdateUserRequestDto;
import com.api.ecomerce.dto.response.UserResponseDto;
import com.api.ecomerce.infra.exception.InvalidCredentialException;
import com.api.ecomerce.infra.exception.UserNotFoundException;
import com.api.ecomerce.infra.security.TokenService;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private WalletService walletService;

    @Mock
    private TokenService tokenService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }


    @Test
    @DisplayName("Should create user successfully when everything is ok")
    void saveUserCase1() {
        SignUpRequestDto request = new SignUpRequestDto("anthony", "anthony@outlook.com", "12345678");
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password("encoded_password")
                .build();

        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal(0), 0, LocalDateTime.now(), user);


        when(authentication.getPrincipal()).thenReturn(user);
        when(authService.getAuthenticatedUser()).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(walletService.createWallet(any(User.class))).thenReturn(wallet);

        User savedUser = userService.saveUser(request);

        verify(userRepository, times(2)).save(any(User.class));
        verify(walletService, times(1)).createWallet(any(User.class));
        verify(passwordEncoder, times(1)).encode(request.getPassword());

        assertEquals(wallet, savedUser.getWallet());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    void auth() throws UserNotFoundException, InvalidCredentialException {
        String savedEmail = "anthony@outlook.com";
        String savedPassword = "encoded_password";

        LoginUserRequestDto request = new LoginUserRequestDto("anthony@outlook.com", "encoded_password");
        User user = User.builder()
                .name("anthony")
                .email(savedEmail)
                .password(savedPassword)
                .build();

       when(userRepository.findByEmail(savedEmail)).thenReturn(Optional.of(user));
       when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
       when(tokenService.generateToken(user)).thenReturn("token");


       String userAuthenticated = this.userService.auth(request);

       verify(userRepository, times(1)).findByEmail(request.getEmail());
       verify(passwordEncoder, times(1)).matches(request.getPassword(), user.getPassword());
       verify(tokenService, times(1)).generateToken(user);

       assertEquals(user.getEmail(), request.getEmail());
       assertEquals(user.getPassword(), request.getPassword());
       assertEquals(userAuthenticated, tokenService.generateToken(user));
    }

    @Test
    @DisplayName("Should return all users when everything is ok")
    void listAllUsersCase1() {
        User user = User.builder()
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.listAllUsers();

        assertFalse(users.isEmpty(), "The user list should contain at least 1 user");
        assertEquals(user, users.get(0), "The user in the list should match the expected user");
    }

    @Test
    void findUserById() throws UserNotFoundException {
        UUID savedId = UUID.randomUUID();
        User user = User.builder()
                .id(savedId)
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();

        when(userRepository.findById(savedId)).thenReturn(Optional.of(user));

        User userFound = userService.findUserById(savedId);

        verify(userRepository, times(1)).findById(savedId);
        assertEquals(userFound, user);

    }

    @Test
    @DisplayName("Should update user when everything is ok")
    void updateUser() {
        UpdateUserRequestDto request = new UpdateUserRequestDto("teste", "12345678");

        User user = User.builder()
                .id(UUID.randomUUID())
                .name("anthony")
                .email("anthony@outlook.com")
                .password("encoded_password")
                .build();
        Authentication auth = mock(Authentication.class);

        when(auth.getPrincipal()).thenReturn("testUser");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");

        SecurityContextHolder.getContext().setAuthentication(auth);
        User userUpdated = userService.updateUser(request);

        verify(authService, times(1)).getAuthenticatedUser();
        assertEquals(request.getName(), userUpdated.getName());
        assertEquals(passwordEncoder.encode(request.getPassword()), userUpdated.getPassword());
    }

    @Test
    void loadUserByUsername() {
    }
}