package com.shopModel.model.Controller;

import com.shopModel.model.Model.Designs;
import com.shopModel.model.Model.Shop;
import com.shopModel.model.OwnerRequestDTO.DesignDTO;
import com.shopModel.model.Service.DesignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/shops/{shopId}/designs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DesignController {

    private final DesignService designService;

    // Add a new design for a specific shop
    @PostMapping
    public ResponseEntity<Designs> addDesign(
            @PathVariable Long shopId,
            @ModelAttribute DesignDTO designDTO,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        Designs saved = designService.addDesign(shopId, designDTO, image);
        return ResponseEntity.ok(saved);
    }


    // Get all designs for a specific shop
    @GetMapping
    public List<Designs> getAll(@PathVariable Long shopId) {
        return designService.getAllDesignsByShop(shopId);
    }

    // Get designs by category for a specific shop
    @GetMapping("/category/{category}")
    public List<Designs> getByCategory(@PathVariable Long shopId, @PathVariable String category) {
        return designService.getDesignsByCategory(shopId, category);
    }

    // Delete a design only if it belongs to this shop
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long shopId, @PathVariable Long id) {
        designService.deleteDesign(id, shopId);
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Designs design = designService.getDesignById(id); // fetch design from service
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + design.getImageName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, design.getImageType())
                .body(design.getImageData());
    }

}
