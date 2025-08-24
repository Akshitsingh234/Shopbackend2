package com.shopModel.model.OwnerRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignDTO {
    private String category;
    private String weight;
    private String type;
    // You can ignore image here if you handle it separately
    // Or you can remove MultipartFile and just use it here too
}
