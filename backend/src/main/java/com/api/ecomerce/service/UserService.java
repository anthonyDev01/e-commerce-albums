package com.api.ecomerce.service;

import com.api.ecomerce.dto.request.LoginUserRequestDto;
import com.api.ecomerce.dto.request.SignUpRequestDto;
import com.api.ecomerce.dto.request.UpdateUserRequestDto;
import com.api.ecomerce.dto.response.LoginResponseDto;
import com.api.ecomerce.infra.exception.InvalidCredentialException;
import com.api.ecomerce.infra.exception.UserAlreadyExistsException;
import com.api.ecomerce.infra.exception.UserNotFoundException;
import com.api.ecomerce.infra.security.TokenService;
import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final WalletService walletService;
    private final AuthService authService;


    public User saveUser(SignUpRequestDto request) {
        log.info("Attempting to create a user");
        verifyIfUserEmailExists(request.getEmail());

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .build();

        this.userRepository.save(newUser);

        Wallet wallet = walletService.createWallet(newUser);

        newUser.setWallet(wallet);

        log.info("User created successfully");
        return this.userRepository.save(newUser);
    }

    public LoginResponseDto auth(LoginUserRequestDto request) throws InvalidCredentialException, UserNotFoundException {
        log.info("Attempting to authenticate a user");
        User user = this.userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            log.error("User with email: {} not found", request.getEmail());
            return new UserNotFoundException("User not found");
        });
        if(!this.passwordEncoder.matches(request.getPassword(), user.getPassword())){
            log.error("Invalid user credentials");
            throw new InvalidCredentialException("Invalid credentials");
        }
        log.info("User authenticated successfully");
        String token = tokenService.generateToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public List<User> listAllUsers(){
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User findUserById(UUID id) throws UserNotFoundException {
        log.info("Fetching user with id: " + id);
        return this.userRepository.findById(id).orElseThrow(() -> {
            log.error("User with id: {} not found", id);
            return new UserNotFoundException("User not found!");});
    }

    public User updateUser(UpdateUserRequestDto request){
        User user = authService.getAuthenticatedUser();

        log.info("Attempting to update user with id: " + user.getId());

        user.setName(request.getName());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));

        log.info("User with id: {} updated successfully", user.getId());
        return this.userRepository.save(user);
    }

    private void verifyIfUserEmailExists(String email){
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()){
            log.error("Email already in use: " + email);
            throw new UserAlreadyExistsException("This email is already in use");
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: " + username);
        Optional<User> optionalUser = this.userRepository.findByEmail(username);

        return optionalUser.orElseThrow(() -> {
                    log.error("User not found with username: " + username);
                    return new UsernameNotFoundException("User not found " + username);
                });
    }
}
