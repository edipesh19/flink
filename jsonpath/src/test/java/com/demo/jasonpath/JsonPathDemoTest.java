package com.demo.jasonpath;


import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

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


    private String jsonData1 = "{\n" +
        "    \"tool\": \n" +
        "    {\n" +
        "        \"jsonpath\": \n" +
        "        {\n" +
        "            \"creator\": \n" +
        "            {\n" +
        "                \"name\": \"Jayway Inc.\",\n" +
        "                \"otp\":\n" +
        "                {\n" +
        "                    \"masterKeyId\":\"masterKeyID\",\n" +
        "                    \"credCypherText\":\"cyphertext\",\n" +
        "                    \"dekCypherText\":\"dekCyphertext\"\n" +
        "                },\n" +
        "                \"location\": \n" +
        "                [\n" +
        "                    \"Malmo\",\n" +
        "                    \"San Francisco\",\n" +
        "                    \"Helsingborg\"\n" +
        "                ]\n" +
        "            },\n" +
        "            \"password\":\n" +
        "            {\n" +
        "                \"masterKeyId\":\"masterKeyID\",\n" +
        "                \"credCypherText\":\"cyphertext\",\n" +
        "                \"dekCypherText\":\"dekCyphertext\"\n" +
        "            }\n" +
        "        }\n" +
        "    },\n" +
        " \n" +
        "    \"book\": \n" +
        "    [\n" +
        "        {\n" +
        "            \"name\": \"Beginning JSON\",\n" +
        "            \"price\": 49.99\n" +
        "        },\n" +
        " \n" +
        "        {\n" +
        "            \"name\": \"JSON Essentials\",\n" +
        "            \"price\": 9.99\n" +
        "        },\n" +
        " \n" +
        "        {\n" +
        "            \"name\": \"JSON at Work\",\n" +
        "            \"price\": 29.99\n" +
        "        }\n" +
        "    ]\n" +
        "}";

    private String jsonData2 = "{\n" +
        "    \"store\": {\n" +
        "        \"book\": [\n" +
        "            {\n" +
        "                \"category\": \"reference\",\n" +
        "                \"author\": \"Nigel Rees\",\n" +
        "                \"title\": \"Sayings of the Century\",\n" +
        "                \"price\": 8.95\n" +
        "            },\n" +
        "            {\n" +
        "                \"category\": \"fiction\",\n" +
        "                \"author\": \"Evelyn Waugh\",\n" +
        "                \"title\": \"Sword of Honour\",\n" +
        "                \"price\": 12.99\n" +
        "            },\n" +
        "            {\n" +
        "                \"category\": \"fiction\",\n" +
        "                \"author\": \"Herman Melville\",\n" +
        "                \"title\": \"Moby Dick\",\n" +
        "                \"isbn\": \"0-553-21311-3\",\n" +
        "                \"price\": 8.99\n" +
        "            },\n" +
        "            {\n" +
        "                \"category\": \"fiction\",\n" +
        "                \"author\": \"J. R. R. Tolkien\",\n" +
        "                \"title\": \"The Lord of the Rings\",\n" +
        "                \"isbn\": \"0-395-19395-8\",\n" +
        "                \"price\": 22.99\n" +
        "            }\n" +
        "        ],\n" +
        "        \"bicycle\": {\n" +
        "            \"color\": \"red\",\n" +
        "            \"price\": 19.95\n" +
        "        }\n" +
        "    },\n" +
        "    \"expensive\": 10\n" +
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

    @Test
    public void testcase3(){
        String jsonPath = "$.tool.jsonpath.creator.name";
        String newJsonData = JsonPathDemo.setData(jsonData, jsonPath, "Oracle Corp");
        assertEquals(JsonPathDemo.getData(newJsonData, jsonPath), "Oracle Corp");
    }

    @Test
    public void testcase4(){
        String jsonPath = "$.tool.jsonpath.password";
        String data = JsonPathDemo.getJsonData(jsonData1, jsonPath);
        System.out.println(data);
    }

    @Test
    public void getJsonPathsTest(){
        String jsonPath = "$..author";
        List<String> expected = Arrays.asList("$['store']['book'][0]['author']",
            "$['store']['book'][1]['author']",
            "$['store']['book'][2]['author']",
            "$['store']['book'][3]['author']");

        List<String> pathList = JsonPathDemo.getJsonPaths(jsonData2, jsonPath);
        // Ensure List
        assertThat(pathList, isA(List.class));

        // Ensure Contents
        assertThat(pathList, is(expected));

        // Ensure Order
        assertThat(pathList, contains("$['store']['book'][0]['author']",
            "$['store']['book'][1]['author']",
            "$['store']['book'][2]['author']",
            "$['store']['book'][3]['author']"));
    }


    @Test
    public void getJsonPathsTest2(){
        String jsonPath = "$..name";
        List<String> expected = Arrays.asList("$['tool']['jsonpath']['creator']['name']",
            "$['book'][0]['name']",
            "$['book'][1]['name']",
            "$['book'][2]['name']");

        List<String> pathList = JsonPathDemo.getJsonPaths(jsonData1, jsonPath);
        // Ensure List
        assertThat(pathList, isA(List.class));

        // Ensure Contents
        assertThat(pathList, is(expected));

        // Ensure Order
        assertThat(pathList, contains("$['tool']['jsonpath']['creator']['name']",
            "$['book'][0]['name']",
            "$['book'][1]['name']",
            "$['book'][2]['name']"));
    }

    @Test
    public void getJsonPathsTest3(){
        String jsonPath = "$.tool.jsonpath.password";
        List<String> expected = Arrays.asList("$['tool']['jsonpath']['password']");

        List<String> pathList = JsonPathDemo.getJsonPaths(jsonData1, jsonPath);

        // Ensure List
        assertThat(pathList, isA(List.class));

        // Ensure Contents
        assertThat(pathList, is(expected));

    }
}
