package com.nab.interview;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nab.interview.api.ProductResponse;
import com.nab.interview.pojo.ProductEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonUtil {


    public static String readJsonFile(String path) {

        InputStream in = TypeReference.class.getResourceAsStream(path);
        BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        try {
            String str;
            while ((str = r.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }


    public static <T> T getObjectFromJson(String json, Class<T> classType) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return mapper.readValue(json, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<ProductResponse> getListOfObjectsFromJson(String json) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return mapper.readValue(json, new TypeReference<List<ProductResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<ProductEntity> getListOfEntityFromJson(String json) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return mapper.readValue(json, new TypeReference<List<ProductEntity>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
