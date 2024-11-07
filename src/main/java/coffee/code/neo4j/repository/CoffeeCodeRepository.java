package coffee.code.neo4j.repository;

import coffee.code.neo4j.entity.SoftwareProduct;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CoffeeCodeRepository extends Neo4jRepository<SoftwareProduct, String> {


    @Query("""
            MATCH (n:SoftwareProduct) RETURN n.name AS SoftwareProductList
            """)
    List<String> findAllSoftwareProductNames();

    @Query("""
            CALL apoc.search.node({SoftwareProduct: 'id'},'exact',$id) YIELD node AS softwareProduct
            MATCH (backend:BackEnd)<-[:hasBackEnd]-(softwareProduct)-[:hasFrontEnd]->(frontend)
            DETACH DELETE softwareProduct, backend, frontend
            """)
    void deleteSoftwareProduct(String id);
}
