package com.shopModel.model.OwnerRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginRequest {
    private String username;
    private String password;
}
