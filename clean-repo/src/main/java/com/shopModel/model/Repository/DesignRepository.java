package com.shopModel.model.Repository;

import com.shopModel.model.Model.Designs;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DesignRepository extends JpaRepository<Designs, Long> {
    List<Designs> findByShopId(Long shopId);
    List<Designs> findByShopIdAndCategory(Long shopId, String category);
    Optional<Designs> findByIdAndShopId(Long id, Long shopId);
    @Transactional
    void deleteByShopId(Long shopId);

}