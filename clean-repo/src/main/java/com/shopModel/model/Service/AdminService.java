package com.shopModel.model.Service;

import com.shopModel.model.Model.Admin;
import com.shopModel.model.Model.Shop;
import com.shopModel.model.OwnerRequestDTO.AdminLoginRequest;
import com.shopModel.model.Repository.AdminRepo;
import com.shopModel.model.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ShopRepository shopRepository;
    public ResponseEntity<Boolean> loginAdmin(AdminLoginRequest dto) {
        Optional<
                        Admin> adminOpt = adminRepo.findByUsername(dto.getUsername());
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getPassword().equals(dto.getPassword())) {
                return ResponseEntity.ok(true); // login success
            } else {
                return ResponseEntity.status(401).body(false); // wrong password
            }
        } else {
            return ResponseEntity.status(404).body(false); // admin not found
        }
    }

    public ResponseEntity<Shop> registerShop(String shopName, String address) {
        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setAddress(address);

        Shop saved = shopRepository.save(shop);
        return ResponseEntity.ok(saved);
    }
}
