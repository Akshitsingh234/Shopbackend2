package com.shopModel.model.OwnerRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    private Long id;
    private String email;
    private String shopName;
}
