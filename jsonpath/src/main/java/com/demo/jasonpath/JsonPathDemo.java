package com.demo.jasonpath;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Option;
import net.minidev.json.JSONObject;

import java.util.List;

import static com.jayway.jsonpath.JsonPath.using;

public class JsonPathDemo {

    private static Configuration configuration = Configuration.builder().
        options(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS)
        .build();
    /*public static String getData(String jsonData, String jsonPath) {
        return JsonPath.parse(jsonData).read(jsonPath);
    }*/


    public static String getData(String jsonData, String jsonPath) {
        DocumentContext json = using(configuration).parse(jsonData);
        return json.read(jsonPath);
    }

    public static String setData(String jsonData, String jsonPath, String newValue){
        DocumentContext json = using(configuration).parse(jsonData);
        return json.set(jsonPath, newValue).jsonString();
    }

    public static String getJsonData(String jsonData, String jsonPath) {
        DocumentContext json = using(configuration).parse(jsonData);
        return json.read(jsonPath, JSONObject.class).toJSONString();
    }

    public static List<String> getJsonPaths(String jsonData, String jsonPath) {
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST, Option.ALWAYS_RETURN_LIST).build();
        DocumentContext json = using(conf).parse(jsonData);
        return json.read(jsonPath);
    }
}
