package com.shopModel.model.Repository;

import com.shopModel.model.Model.Owner;
import com.shopModel.model.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByEmailAndShop(String email, Shop shop);
}
