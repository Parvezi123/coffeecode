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

    public List<SoftwareProduct> getAllSoftwareProducts() {
        return coffeeCodeRepository.findAll();
    }

    public Optional<SoftwareProduct> getSoftwareProductById(Long id) {
        return coffeeCodeRepository.findById(id);
    }

    public SoftwareProduct createSoftwareProduct(ProductDetails productDetails) {
        SoftwareProduct softwareProduct = SoftwareProduct.builder()
                .name(productDetails.getName())
                .version(productDetails.getVersion())
                .frontEnd(productDetails.getFrontEnd())
                .backEnd(productDetails.getBackEnd())
                .build();
        return coffeeCodeRepository.save(softwareProduct);
    }

    public void deleteProduct(Long id) {
        coffeeCodeRepository.deleteById(id);
    }
}
