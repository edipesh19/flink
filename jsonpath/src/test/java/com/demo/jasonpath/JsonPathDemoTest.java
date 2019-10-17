package com.demo.jasonpath;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonPathDemoTest {

    private String jsonData =  "{\n" +
            "    \"tool\": \n" +
            "    {\n" +
            "        \"jsonpath\": \n" +
            "        {\n" +
            "            \"creator\": \n" +
            "            {\n" +
            "                \"name\": \"Jayway Inc.\",\n" +
            "                \"location\": \n" +
            "                [\n" +
            "                    \"Malmo\",\n" +
            "                    \"San Francisco\",\n" +
            "                    \"Helsingborg\"\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            " \n" +
            "    \"book\": \n" +
            "    [\n" +
            "        {\n" +
            "            \"title\": \"Beginning JSON\",\n" +
            "            \"price\": 49.99\n" +
            "        },\n" +
            " \n" +
            "        {\n" +
            "            \"title\": \"JSON at Work\",\n" +
            "            \"price\": 29.99\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void testcase1(){
        String jsonPath = "$.tool.jsonpath.creator.name";
        assertEquals(JsonPathDemo.getData(jsonData, jsonPath), "Jayway Inc.");
    }

    @Test
    public void testcase2(){
        String jsonPath = "$.tool.jsonpath.creator.location[2]";
        assertEquals(JsonPathDemo.getData(jsonData, jsonPath), "Helsingborg");
    }
}
