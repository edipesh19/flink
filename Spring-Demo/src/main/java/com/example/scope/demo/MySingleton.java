package com.example.scope.demo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MySingleton implements IMySingleton {

    @Autowired
    private ObjectFactory<MyPrototype> prototypeBeanObjectFactory;

    private MyPrototype prototype;

    public MySingleton() {
    }

    @Override
    public void changeData(){
        prototype = prototypeBeanObjectFactory.getObject();
        prototype.setData();
    }

    @Override
    public String toString() {
        return "MySingleton{" +
            "prototype=" + prototype +
            '}';
    }
}
