package coffee.code.neo4j.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class TechStackEndInfo {
    private List<String> languages;
    private List<String> frameworks;
}
