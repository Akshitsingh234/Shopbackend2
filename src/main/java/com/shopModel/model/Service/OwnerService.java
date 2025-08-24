package com.shopModel.model.Service;

import com.shopModel.model.Model.Owner;
import com.shopModel.model.Model.Shop;
import com.shopModel.model.OwnerRequestDTO.OwnerDTO;
import com.shopModel.model.OwnerRequestDTO.OwnerRequestDTO;
import com.shopModel.model.Repository.OwnerRepository;
import com.shopModel.model.Repository.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
     @Autowired
    private ShopRepository shopRepository;




    public ResponseEntity<?> registerOwner(OwnerRequestDTO dto) {

        // check if already exists
        Optional<Owner> existing = ownerRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Owner already exists"); // already registered
        }
        System.out.println("Received request: " + dto);

        // find the shop by ID
        Optional<Shop> shopOpt = shopRepository.findById(dto.getShopId());
        if (shopOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("not registered"); // shop not found
        }

        // create new owner
        Owner owner = new Owner();
        owner.setEmail(dto.getEmail());
        owner.setPassword(dto.getPassword());
        owner.setShop(shopOpt.get());   // ✅ link to Shop entity

        ownerRepository.save(owner);

        return ResponseEntity.ok("✅ Owner registered successfully!"); // success
    }





    public ResponseEntity<Boolean> loginOwner(OwnerRequestDTO dto, Long shopId) {
        Optional<Shop> shopOpt = shopRepository.findById(shopId);
        if (shopOpt.isEmpty()) {
            return ResponseEntity.status(404).body(false); // shop not found
        }

        Optional<Owner> ownerOpt = ownerRepository.findByEmailAndShop(dto.getEmail(), shopOpt.get());

        if (ownerOpt.isPresent()) {
            Owner owner = ownerOpt.get();
            if (owner.getPassword().equals(dto.getPassword())) {
                return ResponseEntity.ok(true); // ✅ login success
            } else {
                return ResponseEntity.status(401).body(false); // ❌ wrong password
            }
        } else {
            return ResponseEntity.status(404).body(false); // ❌ owner not found for this shop
        }
    }

    @Transactional
    public ResponseEntity<Boolean> deleteOwner(Long ownerId) {
        Optional<Owner> ownerOpt = ownerRepository.findById(ownerId);

        if (ownerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Owner owner = ownerOpt.get();

        // Delete shop if exists
        if (owner.getShop() != null) {
            shopRepository.delete(owner.getShop());
        }

        // Delete owner
        ownerRepository.delete(owner);

        return ResponseEntity.ok(true);
    }



    public List<Owner> getAllOwners() {
        return  ownerRepository.findAll();
    }

    public List<OwnerDTO> getAllOwner() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(owner -> new OwnerDTO(
                        owner.getId(),
                        owner.getEmail(),
                        owner.getShop() != null ? owner.getShop().getShopName() : null
                ))
                .collect(Collectors.toList());
    }

}
