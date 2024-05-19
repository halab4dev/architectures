package com.github.halab4dev.common.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * @author halab
 */
public class ModelMapperUtils {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtils() {
    }

    /**
     * Return new object of given class with attribute value copy from input object
     *
     * @param obj  input object which attributes are copied from
     * @param type type of output object
     * @return new object of given class with attribute value copy from input object
     */
    public static <T> T toObject(Object obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        T t = null;
        try {
            t = MODEL_MAPPER.map(obj, type);
        } catch (Exception ex) {
            SimpleLogger.error(ex);
        }
        return t;
    }
}
