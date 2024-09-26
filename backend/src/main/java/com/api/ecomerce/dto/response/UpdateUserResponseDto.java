package com.api.ecomerce.dto.response;

import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDto {
    private UUID id;
    private String name;

    public UpdateUserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
