//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.util.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = null;
    private static ObjectMapper genericObjectMapper = null;

    public JsonUtils() {
    }

    private static ObjectMapper initObjectMapper() {
        ObjectMapper newObjectMapper = new ObjectMapper();
        newObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        newObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        newObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        newObjectMapper.setSerializationInclusion(Include.NON_NULL);
        newObjectMapper.disable(new MapperFeature[]{MapperFeature.USE_GETTERS_AS_SETTERS});
        return newObjectMapper;
    }

    public static String toJsonStr(Object value) {
        return toJsonStr(value, objectMapper);
    }

    public static String toJsonStrGeneric(Object value) {
        return toJsonStr(value, genericObjectMapper);
    }

    private static String toJsonStr(Object value, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException var3) {
            log.error("Json转换失败", var3);
            throw new RuntimeException(var3);
        }
    }

    public static byte[] toJsonBytes(Object value) {
        return toJsonBytes(value, objectMapper);
    }

    public static byte[] toJsonBytesGeneric(Object value) {
        return toJsonBytes(value, genericObjectMapper);
    }

    private static byte[] toJsonBytes(Object value, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (JsonProcessingException var3) {
            log.error("Json转换失败", var3);
            throw new RuntimeException(var3);
        }
    }

    public static String toJsonStr(Object value, String[] properties) {
        try {
            SimpleBeanPropertyFilter e = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
            SimpleFilterProvider filterProvider = (new SimpleFilterProvider()).addFilter("propertyFilterMixIn", e);
            return objectMapper.writer(filterProvider).writeValueAsString(value);
        } catch (Exception var4) {
            log.error("Json转换失败", var4);
            throw new RuntimeException(var4);
        }
    }

    public static String toJsonStrWithExcludeProperties(Object value, String[] properties2Exclude) {
        try {
            SimpleBeanPropertyFilter e = SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude);
            SimpleFilterProvider filterProvider = (new SimpleFilterProvider()).addFilter("propertyFilterMixIn", e);
            return objectMapper.writer(filterProvider).writeValueAsString(value);
        } catch (Exception var4) {
            log.error("Json转换失败", var4);
            throw new RuntimeException(var4);
        }
    }

    public static void writeJsonStr(OutputStream out, Object value) {
        try {
            objectMapper.writeValue(out, value);
        } catch (Exception var3) {
            log.error("Json转换失败", var3);
            throw new RuntimeException(var3);
        }
    }


    public static Object fromJsonGeneric(String jsonString) {
        return fromJson(jsonString, Object.class, genericObjectMapper);
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return fromJson(jsonString, clazz, objectMapper);
    }

    private static <T> T fromJson(String jsonString, Class<T> clazz, ObjectMapper objectMapper) {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                log.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz, Class... elementClasses) {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return elementClasses.length == 0?objectMapper.readValue(jsonString, clazz):objectMapper.readValue(jsonString, getGenericsType(clazz, elementClasses));
            } catch (IOException var4) {
                log.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public static JavaType getGenericsType(Class<?> collectionClass, Class... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static JsonNode getjsonvalue(String jsonText, String key) {
        try {
            ObjectMapper e = new ObjectMapper();
            JsonNode rootNode = e.readTree(jsonText);
            return rootNode.path(key);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> readJsonList(String jsondata, Class<?> collectionClass) {
        try {
            ObjectMapper e = new ObjectMapper();
            JavaType javaType = getGenericsType(ArrayList.class, new Class[]{collectionClass});
            List lst = (List)e.readValue(jsondata, javaType);
            return lst;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> readJsonMap(String jsondata) {
        try {
            Map e = (Map)objectMapper.readValue(jsondata, Map.class);
            log.debug(e.toString());
            return e;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    static {
        objectMapper = initObjectMapper();
        genericObjectMapper = initObjectMapper();
        genericObjectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.WRAPPER_OBJECT);
    }
}
