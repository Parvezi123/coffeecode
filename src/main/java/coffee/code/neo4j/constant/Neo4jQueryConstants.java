package coffee.code.neo4j.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Neo4jQueryConstants {

    public static final String QUERY_TO_GET_A_SOFTWARE_PRODUCT =

        """
        MATCH (fe:FrontEnd)<-[:hasFrontEnd]-(n:SoftwareProduct{id: $id})-[:hasBackEnd]->(bk:BackEnd)
        RETURN
            {name: n.name, version: n.version,
                frontEndInfo: {languages: fe.languages, frameworks: fe.frameworks},
                backEndInfo: bk {languages: bk.languages, frameworks: bk.frameworks}} AS result
        """;
    public static final String QUERY_TO_GET_COMPLETE_SOFTWARE_PRODUCTS_INFO =

            """
            MATCH (fe:FrontEnd)<-[:hasFrontEnd]-(n:SoftwareProduct)-[:hasBackEnd]->(bk:BackEnd)
        RETURN
            {name: n.name, version: n.version,
                frontEndInfo: {languages: fe.languages, frameworks: fe.frameworks},
                backEndInfo: bk {languages: bk.languages, frameworks: bk.frameworks}} AS result
        """;
}
