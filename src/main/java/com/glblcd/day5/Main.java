package com.glblcd.day5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String location = "https://xkcd.com/info.0.json";
        var client = HttpClient.newBuilder().build();
        URI uri = null;
        try {
            uri = new URI(location);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        var req = HttpRequest.newBuilder(uri).build();

        try {
            var response = client.send(req, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));
            var jsonData = parseJsonToMap(response.body());
            for (String key : jsonData.keySet()) {
                System.out.println( key + ": " + jsonData.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        parseJsonToMap(loadJsonStringFromFile("src/main/resources/cake.json"));
    }

    private String loadJsonStringFromFile(String sourcePath){
        String json = null;
        try {
            byte[] contents = Files.readAllBytes(Paths.get(sourcePath));
            json = new String(contents);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return json;
    }

    public static Map<String, Object> parseJsonToMap(String json) {
        var mapper = new ObjectMapper();
        var typeRef = new TypeReference<HashMap<String,Object>>() {};

        try {
            Map<String, Object> mJson = mapper.readValue(json, typeRef);
            // System.out.println(mJson);
            return mJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
