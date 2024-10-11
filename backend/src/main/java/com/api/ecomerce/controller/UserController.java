package com.api.ecomerce.controller;
import com.api.ecomerce.documentation.UserDocumentation;
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
import com.api.ecomerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserDocumentation {

    private final UserService userService;

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> singUpUser(@Valid @RequestBody SignUpRequestDto body){
        User user = this.userService.saveUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponseDto(user));
    }

    @Override
    @PostMapping("/auth")
    public ResponseEntity<LoginResponseDto> authUser(@Valid @RequestBody LoginUserRequestDto body) throws UserNotFoundException, InvalidCredentialException {
        LoginResponseDto response = this.userService.auth(body);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<User> users = this.userService.listAllUsers();
        List<UserResponseDto> userResponseDtos = UserResponseDto.fromUserList(users);
        return ResponseEntity.ok(userResponseDtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) throws UserNotFoundException {
        User user = this.userService.findUserById(id);
        return ResponseEntity.ok(new UserResponseDto(user));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@Valid @RequestBody UpdateUserRequestDto body){
        User user = this.userService.updateUser(body);
        return ResponseEntity.ok(new UpdateUserResponseDto(user));
    }
}
