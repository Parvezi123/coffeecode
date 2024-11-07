package coffee.code.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Node(primaryLabel = "FrontEnd")
public class FrontEnd {
    @Id
    private String id;
    private List<String> languages;
    private List<String> frameworks;
}
