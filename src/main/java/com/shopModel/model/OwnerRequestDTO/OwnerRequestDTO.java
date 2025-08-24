package com.shopModel.model.OwnerRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequestDTO {
    private String email;
    private String password;
    private Long shopId;   // ✅ add this
}
