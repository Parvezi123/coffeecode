package coffee.code.neo4j.repository;

import coffee.code.neo4j.entity.SoftwareProduct;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CoffeeCodeRepository extends Neo4jRepository<SoftwareProduct, Long> {

    Optional<SoftwareProduct> findByName(String name);
}
