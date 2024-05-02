package zw.co.zim.willplatform.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static JsonUtil instance;

    private JsonUtil() {
        super();
    }

    public static JsonUtil getInstance() {
        if (instance == null) {
            instance = new JsonUtil();
        }
        return instance;

    }

    public String convertToJson(Object object) {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        // LOGGER.info( "Parsing Object: "+ object);
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(object);
            //  return mapper.readValue(stringValue,String.class);
        } catch (IOException e) {
            LOGGER.error("Failed to parse :", e);
            return null;
        }
    }

    public String prettyPrint(final String json) {
        if (StringUtils.isEmpty(json)) {
            return json;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            final Object jsonObject = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            LOGGER.error("Failed to parse :", e);
            return json;
        }
    }

    public <T> T convertToObject(String jsonString, T object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return (T) mapper.readValue(jsonString, object.getClass());
        } catch (IOException e) {
            LOGGER.error("Failed to convert json string : ", e);
            return null;
        }
    }

    public String prettyPrint(Object object) {

        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            String json = mapper.writeValueAsString(object);
            final Object jsonObject = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

        } catch (IOException e) {
            LOGGER.error("Failed to parse :", e);
            return null;
        }
    }
}
