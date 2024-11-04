package coffee.code.neo4j.controller;

import coffee.code.neo4j.dto.ProductDetails;
import coffee.code.neo4j.entity.SoftwareProduct;
import coffee.code.neo4j.service.CoffeeCodeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee-code")
@RequiredArgsConstructor
@AllArgsConstructor
public class CoffeeCodeController {

private CoffeeCodeService coffeeCodeService;

    @GetMapping
    public List<SoftwareProduct> getAllProducts() {
        return coffeeCodeService.getAllSoftwareProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftwareProduct> getProductById(@PathVariable Long id) {
        return coffeeCodeService.getSoftwareProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SoftwareProduct createProduct(@RequestBody ProductDetails productDetails) {
        return coffeeCodeService.createSoftwareProduct(productDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        coffeeCodeService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
