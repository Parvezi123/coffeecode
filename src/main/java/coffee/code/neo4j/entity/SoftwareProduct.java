package coffee.code.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Node(primaryLabel = "SoftwareProduct")
public class SoftwareProduct {
    @Id
    private String id;
    private String name;
    private String version;
    @Relationship(type = "hasFrontEnd")
    private FrontEnd frontEnd;
    @Relationship(type = "hasBackEnd")
    private BackEnd backEnd;
}
