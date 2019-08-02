package com.glblcd.day5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }

    private void run() {
        String json = "";
        try {
            byte[] contents = Files.readAllBytes(Paths.get("src/main/resources/cake.json"));
            json = new String(contents);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // System.out.println(json);

        convertStringToJson(json);
    }

    private void convertStringToJson(String jsonString) {
        // ObjectMapper mapper = new ObjectMapper();
        // TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

        var mapper = new ObjectMapper();
        var typeRef = new TypeReference<HashMap<String, Object>>() {};
        try {
            var mJson = mapper.readValue(jsonString, typeRef);
            System.out.println(mJson);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
