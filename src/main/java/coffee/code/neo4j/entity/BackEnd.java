package coffee.code.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Node(primaryLabel = "BackEnd")
public class BackEnd {
    private List<String> languages;
    private List<String> frameworks;
}
