package coffee.code.neo4j.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetails {

    private String name;
    private String version;
    private TechStackEndInfo frontEndInfo;
    private TechStackEndInfo backEndInfo;
}
