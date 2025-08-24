package com.shopModel.model.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopName;
    private String address;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference   // ✅ prevents infinite loop
    private Owner owner;
}
