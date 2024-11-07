package coffee.code.neo4j.controller;

import coffee.code.neo4j.dto.ProductDetails;
import coffee.code.neo4j.service.CoffeeCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee-code")
@RequiredArgsConstructor
public class CoffeeCodeController {

private final CoffeeCodeService coffeeCodeService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllSoftwareProductNames() {
        return ResponseEntity.ok(coffeeCodeService.getAllSoftwareProductNames());
    }

    @GetMapping
    public ResponseEntity<List<ProductDetails>> getAllSoftwareProducts() {
        return ResponseEntity.ok(coffeeCodeService.getCompleteSoftwareProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetails> getCompleteSoftwareProductById(@PathVariable String id) {
        return ResponseEntity.ok(coffeeCodeService.getCompleteSoftwareProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDetails productDetails) {
        coffeeCodeService.createSoftwareProduct(productDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        coffeeCodeService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
