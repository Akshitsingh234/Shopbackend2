package com.shopModel.model.Controller;

import com.shopModel.model.Model.Shop;
import com.shopModel.model.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin(origins = "http://localhost:3000")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Get all shops
    @GetMapping
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    // Get shop by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        return shopService.getShopById(id);
    }

    // Delete shop by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        return shopService.deleteShop(id);
    }

    // Delete all shops
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllShops() {
        return shopService.deleteAllShops();
    }
}
