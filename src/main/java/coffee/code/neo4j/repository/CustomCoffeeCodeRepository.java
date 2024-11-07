package coffee.code.neo4j.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomCoffeeCodeRepository {

    <T> T getSingleDTOMappingAsResult(String query, Map<String, Object> parameters, String resultKey, Class<T> targetClass);

    <T> T getSingleDTOMappingAsResultForReadTransactions(String query, Map<String, Object> parameters, String resultKey, Class<T> targetclass);

    <T> List<T> getAggregateDTOMappingAsResult(String query, Map<String, Object> parameters, String resultKey, Class<T> targetClass);

    <T> void updatePartialOrCompleteSoftwareProductEntry(Class<?> entity, T instance);

    Optional<Map<String, Object>> getResultUsingNeo4jClient(String query, Map<String, Object> parameters);

}
