package coffee.code.neo4j.service;

import coffee.code.neo4j.dto.ProductDetails;
import coffee.code.neo4j.entity.SoftwareProduct;
import coffee.code.neo4j.repository.CoffeeCodeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeCodeService {

    private CoffeeCodeRepository coffeeCodeRepository;

    public List<ProductDetails> getAllSoftwareProducts() {
        return coffeeCodeRepository.findAllSoftwareProducts();
    }

    public Optional<ProductDetails> getSoftwareProductById(String id) {
        return coffeeCodeRepository.findSoftwareProductById(id);
    }

    public SoftwareProduct createSoftwareProduct(SoftwareProduct softwareProduct) {
        return coffeeCodeRepository.save(softwareProduct);
    }

    public void deleteProduct(Long id) {
        coffeeCodeRepository.deleteById(id);
    }
}
