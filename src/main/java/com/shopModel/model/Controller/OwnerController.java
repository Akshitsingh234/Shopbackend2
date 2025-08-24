package com.shopModel.model.Controller;

import com.shopModel.model.Model.Owner;
import com.shopModel.model.OwnerRequestDTO.OwnerDTO;
import com.shopModel.model.OwnerRequestDTO.OwnerRequestDTO;
import com.shopModel.model.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@RequestBody OwnerRequestDTO dto) {
        return ownerService.registerOwner(dto);
    }


     @GetMapping("/all")
     public List<Owner> getAll(){
        return ownerService.getAllOwners();
     }


    @PostMapping("/login/{shopId}")
    public ResponseEntity<Boolean> loginOwner(@RequestBody OwnerRequestDTO dto,
                                              @PathVariable Long shopId) {
        return ownerService.loginOwner(dto, shopId);
    }
    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable Long ownerId) {
        return ownerService.deleteOwner(ownerId);
    }
    @GetMapping("/owners")
    public List<OwnerDTO> getOwners() {
        return ownerService.getAllOwner();
    }

}
