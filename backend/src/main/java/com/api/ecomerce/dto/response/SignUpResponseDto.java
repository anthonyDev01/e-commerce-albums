package com.api.ecomerce.dto.response;


import com.api.ecomerce.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private UUID id;
    private String name;
    private UUID wallet_id;

    public SignUpResponseDto(User request){
        this.id = request.getId();
        this.name = request.getName();
        this.wallet_id = request.getWallet().getId();
    }
}
