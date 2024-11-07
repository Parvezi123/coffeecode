package coffee.code.neo4j.service;

import coffee.code.neo4j.constant.ApplicationConstant;
import coffee.code.neo4j.dto.ProductDetails;
import coffee.code.neo4j.entity.BackEnd;
import coffee.code.neo4j.entity.FrontEnd;
import coffee.code.neo4j.entity.SoftwareProduct;
import coffee.code.neo4j.repository.CoffeeCodeRepository;
import coffee.code.neo4j.repository.CustomCoffeeCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static coffee.code.neo4j.constant.Neo4jQueryConstants.QUERY_TO_GET_A_SOFTWARE_PRODUCT;
import static coffee.code.neo4j.constant.Neo4jQueryConstants.QUERY_TO_GET_COMPLETE_SOFTWARE_PRODUCTS_INFO;
import static java.util.Map.entry;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class CoffeeCodeService {

    private final CoffeeCodeRepository coffeeCodeRepository;
    private final CustomCoffeeCodeRepository customCoffeeCodeRepository;

    public List<String> getAllSoftwareProductNames() {
        return coffeeCodeRepository.findAllSoftwareProductNames();
    }

    @Transactional(readOnly = true)
    public ProductDetails getCompleteSoftwareProductById(String id) {
        Map<String, Object> parameters = Map.ofEntries(entry("id", id));
        return customCoffeeCodeRepository.getSingleDTOMappingAsResultForReadTransactions(
                QUERY_TO_GET_A_SOFTWARE_PRODUCT, parameters, ApplicationConstant.RESULT_NAME, ProductDetails.class);
    }

    public List<ProductDetails> getCompleteSoftwareProducts() {
        return customCoffeeCodeRepository.getAggregateDTOMappingAsResult(
                QUERY_TO_GET_COMPLETE_SOFTWARE_PRODUCTS_INFO, Collections.emptyMap(), ApplicationConstant.RESULT_NAME,
                ProductDetails.class
        );
    }

    public void createSoftwareProduct(ProductDetails productDetails) {
        SoftwareProduct softwareProduct = mapToSoftwareproduct(productDetails);
        saveOrUpdateSoftwareProduct(softwareProduct);
    }

    private static SoftwareProduct mapToSoftwareproduct(ProductDetails productDetails) {
        SoftwareProduct softwareProduct = new SoftwareProduct();
        softwareProduct.setId(generateUUID());
        softwareProduct.setName(productDetails.getName());
        softwareProduct.setVersion(productDetails.getVersion());
        softwareProduct.setFrontEnd(FrontEnd.builder().id(generateUUID())
                .languages(productDetails.getFrontEndInfo().getLanguages())
                .frameworks(productDetails.getFrontEndInfo().getFrameworks()).build());
        softwareProduct.setBackEnd(BackEnd.builder().id(generateUUID())
                .languages(productDetails.getBackEndInfo().getLanguages())
                .frameworks(productDetails.getBackEndInfo().getFrameworks()).build());
        return softwareProduct;
    }

    private void saveOrUpdateSoftwareProduct(SoftwareProduct softwareProduct) {
        customCoffeeCodeRepository.updatePartialOrCompleteSoftwareProductEntry(SoftwareProduct.class, softwareProduct);
    }

    private static String generateUUID() {
        return randomUUID().toString();
    }

    public void deleteProduct(String id) {
        coffeeCodeRepository.deleteSoftwareProduct(id);
    }

}
