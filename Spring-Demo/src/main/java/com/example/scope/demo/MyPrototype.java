package com.example.scope.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Scope("prototype")
public class MyPrototype {

    private String name;

    private List<String> list = new ArrayList<>();

    public void setData(){
        list.add(UUID.randomUUID().toString());
        name = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "MyPrototype{" +
            "name='" + name + '\'' +
            ", list=" + list +
            '}';
    }
}
