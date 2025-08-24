package com.shopModel.model.Service;

import com.shopModel.model.Model.Designs;
import com.shopModel.model.Model.Shop;
import com.shopModel.model.OwnerRequestDTO.DesignDTO;
import com.shopModel.model.Repository.DesignRepository;
import com.shopModel.model.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DesignService {

    @Autowired
    private DesignRepository designRepository;
    @Autowired
    private ShopRepository shopRepository;

    // Add a new design
    public Designs addDesign(Long shopId, DesignDTO dto, MultipartFile image) throws IOException {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Designs design = new Designs();
        design.setCategory(dto.getCategory());
        design.setWeight(dto.getWeight());
        design.setType(dto.getType());

        if (image != null && !image.isEmpty()) {
            design.setImageName(image.getOriginalFilename());
            design.setImageType(image.getContentType());
            design.setImageData(image.getBytes());
        }

        design.setShop(shop);
        return designRepository.save(design);
    }

    // Get designs by category under a specific shop
    public List<Designs> getDesignsByCategory(Long shopId, String category) {
        return designRepository.findByShopIdAndCategory(shopId, category);
    }

    // Get all designs for a shop
    public List<Designs> getAllDesignsByShop(Long shopId) {
        return designRepository.findByShopId(shopId);
    }

    // Delete a design but ensure it belongs to the given shop
    public void deleteDesign(Long id, Long shopId) {
        Designs design = designRepository.findByIdAndShopId(id, shopId)
                .orElseThrow(() -> new IllegalArgumentException("Design not found for this shop"));

        designRepository.delete(design);
    }
    public Designs getDesignById(Long id) {
        return designRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Design not found with id: " + id));
    }
    public Designs getDesignImage(Long id) {
        return designRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Design not found"));
    }
}
