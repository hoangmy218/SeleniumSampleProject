package driver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataLoader {
    public static <T> T loadData(String jsonPath, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    JSONDataLoader.class.getClassLoader().getResourceAsStream(jsonPath),
                    clazz
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON file: " + jsonPath, e);
        }
    }
}
