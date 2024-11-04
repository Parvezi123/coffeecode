package coffee.code.neo4j.dto;

import coffee.code.neo4j.entity.BackEnd;
import coffee.code.neo4j.entity.FrontEnd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetails {

    private String name;
    private String version;
    private FrontEnd frontEnd;
    private BackEnd backEnd;
}
