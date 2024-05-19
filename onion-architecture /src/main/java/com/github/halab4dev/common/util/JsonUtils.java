package com.github.halab4dev.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Collection;
import java.util.Map;

/*
 *
 * @author halab
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper()
        .findAndRegisterModules()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private JsonUtils() {
    }

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        String json = null;
        try {
            json = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            SimpleLogger.error("Error when convert object to json", ex);
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        T t = null;
        try {
            t = MAPPER.readValue(json, type);
        } catch (Exception ex) {
            SimpleLogger.error("Error when parse json to object", ex);
        }
        return t;
    }

    public static <T> T toObject(String json, Class<T> type, Class<?>... parameterTypes) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        T t = null;
        try {
            JavaType javaType = constructParametricType(type, parameterTypes);
            t = MAPPER.readValue(json, javaType);
        } catch (Exception ex) {
            SimpleLogger.error("Error when parse json to object", ex);
        }
        return t;
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> keyType, Class<V> valueType) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        Map<K, V> map = null;
        try {
            JavaType javaType = constructParametricType(Map.class, keyType, valueType);
            map = MAPPER.readValue(json, javaType);
        } catch (Exception ex) {
            SimpleLogger.error("Error when parse json to object", ex);
        }
        return map;
    }

    private static JavaType constructParametricType(Class<?> rawType, Class<?>... parameterTypes) {
        return MAPPER.getTypeFactory().constructParametricType(rawType, parameterTypes);
    }

    public static <T extends Collection<E>, E> T toCollection(String json, Class<T> collectionType, Class<E> elememtType) {
        T t = null;
        JavaType type = constructCollectionType(collectionType, elememtType);
        try {
            t = MAPPER.readValue(json, type);
        } catch (Exception ex) {
            SimpleLogger.error("Error when parse json to collection", ex);
        }
        return t;
    }

    @SuppressWarnings("rawtypes")
    private static JavaType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementType) {
        return MAPPER.getTypeFactory().constructCollectionType(collectionClass, elementType);
    }
}
