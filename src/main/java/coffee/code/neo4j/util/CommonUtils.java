package coffee.code.neo4j.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtils {

    private final ObjectMapper mapper;

    public <T> T convertValue(Object content, Class<T> toValueType) {
        var response = mapper.convertValue(content, toValueType);
        if (response == null) {
            throw new IllegalStateException("Converted reference value cannot be null");
        }
        return  response;
    }

    public <T> T convertValue(Object content, TypeReference<T> toValueType) {
        var response = mapper.convertValue(content, toValueType);
        if (response == null) {
            throw new IllegalStateException("Converted reference value cannot be null");
        }
        return response;
    }
}
