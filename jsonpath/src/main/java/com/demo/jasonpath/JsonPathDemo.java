package com.demo.jasonpath;

import com.jayway.jsonpath.JsonPath;

public class JsonPathDemo {
    public static String getData(String jsonData, String jsonPath) {
        return JsonPath.parse(jsonData).read(jsonPath);
    }
}
