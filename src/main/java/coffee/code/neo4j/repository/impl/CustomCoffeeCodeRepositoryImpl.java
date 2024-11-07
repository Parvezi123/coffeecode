package coffee.code.neo4j.repository.impl;

import coffee.code.neo4j.repository.CustomCoffeeCodeRepository;
import coffee.code.neo4j.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomCoffeeCodeRepositoryImpl implements CustomCoffeeCodeRepository {

    private final Neo4jTemplate neo4jTemplate;
    private final Neo4jClient client;
    private final CommonUtils commonUtils;


    @Override
    public <T> T getSingleDTOMappingAsResult(String query, Map<String, Object> parameters, String resultKey, Class<T> targetClass) {
        return getSingleDTOAsResult(query, parameters, resultKey, targetClass);
    }

    private <T> T getSingleDTOAsResult(String query, Map<String, Object> parameters, String resultKey, Class<T> targetClass) {
        return client.query(query).bindAll(parameters)
                .fetchAs(targetClass).mappedBy(((typeSystem, recordData) ->
                        commonUtils.convertValue(recordData.get(resultKey).asObject(), targetClass)))
                .one().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T getSingleDTOMappingAsResultForReadTransactions(String query, Map<String, Object> parameters, String resultKey, Class<T> targetclass) {
        return getSingleDTOAsResult(query, parameters, resultKey, targetclass);
    }

    @Override
    public <T> List<T> getAggregateDTOMappingAsResult(String query, Map<String, Object> parameters, String resultKey, Class<T> targetClass) {
        return List.copyOf(client.query(query).bindAll(parameters)
                .fetchAs(targetClass).mappedBy(((typeSystem, recordData) ->
                        commonUtils.convertValue(recordData.get(resultKey).asObject(), targetClass)))
                .all());
    }

    @Override
    public Optional<Map<String, Object>> getResultUsingNeo4jClient(String query, Map<String, Object> parameters) {
        return client.query(query).bindAll(parameters).fetch().one();
    }

    @Override
    public <T> void updatePartialOrCompleteSoftwareProductEntry(Class<?> targetEntity, T requestBodyInstance) {
        neo4jTemplate.save(targetEntity).one(requestBodyInstance);
    }
}
