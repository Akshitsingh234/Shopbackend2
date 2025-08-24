package com.shopModel.model.Service;

import com.shopModel.model.Model.Shop;
import com.shopModel.model.Repository.DesignRepository;
import com.shopModel.model.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    DesignRepository designRepository;

    // Get all shops
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get shop by ID
    public ResponseEntity<Shop> getShopById(Long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete shop by ID
    public ResponseEntity<Void> deleteShop(Long id) {

        if (shopRepository.existsById(id)) {
            designRepository.deleteByShopId(id);
            shopRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete all shops
    public ResponseEntity<Void> deleteAllShops() {
        designRepository.deleteAll();
        shopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
