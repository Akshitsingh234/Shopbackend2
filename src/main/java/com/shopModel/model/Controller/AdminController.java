package com.shopModel.model.Controller;

import com.shopModel.model.OwnerRequestDTO.AdminLoginRequest;
import com.shopModel.model.Model.Shop;
import com.shopModel.model.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<Boolean> loginAdmin(@RequestBody AdminLoginRequest dto) {
        return adminService.loginAdmin(dto);
    }

    // ✅ Register new shop
    @PostMapping("/shop/register")
    public ResponseEntity<Shop> registerShop(@RequestBody Map<String, String> request) {
        String shopName = request.get("shopName");
        String address = request.get("address");
        return adminService.registerShop(shopName, address);
    }

}
