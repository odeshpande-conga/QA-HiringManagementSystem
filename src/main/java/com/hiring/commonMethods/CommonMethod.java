package com.hiring.commonMethods;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonMethod {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Reads a JSON file and returns its content as a Map with String values.
     *
     * @param filePath path to the JSON file
     * @return HashMap containing the JSON key-value pairs as Strings
     */
    public static HashMap<String, String> readTestData(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<HashMap<String, String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath + " | Error: " + e.getMessage());
        }
    }

}
