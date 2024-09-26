package com.api.ecomerce.dto.response;

import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private UUID wallet_id;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.wallet_id = user.getWallet().getId();
    }

    public static List<UserResponseDto> fromUserList(List<User> users) {
        return users.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
