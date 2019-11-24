package com.example.scope.demo;

import com.example.scope.demo.event.AnotherCustomSpringEvent;
import com.example.scope.demo.event.AnotherCustomSpringEventPublisher;
import com.example.scope.demo.event.CustomSpringEvent;
import com.example.scope.demo.event.CustomSpringEventPublisher;
import com.example.scope.demo.profile.ProfileTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private Logger log = LoggerFactory.getLogger(MyController.class.getName());


    @Autowired
    private IMySingleton component;
    @Autowired
    private CustomSpringEventPublisher publisher;
    @Autowired
    private AnotherCustomSpringEventPublisher anotherPublisher;

    @Autowired
    private ProfileTest profile;

    @GetMapping("/hello")
    public String getData(){
        log.info("*** hello controller called ***");
        component.changeData();
        publisher.publish(new CustomSpringEvent(publisher, "Custom event"));
        return component.toString();
    }

    @GetMapping("/profile")
    public String getProfile(){
        String ret =  String.format("*** Name = %s Desc = %s Profile = %s ***", profile.getAppName(), profile.getAppDescription(), profile.getCurrentProfile());
        return ret;
    }

    @GetMapping("/hi")
    public String getData1() {
        log.info("*** hi controller called ***");
        anotherPublisher.publish(new AnotherCustomSpringEvent(anotherPublisher, "Another Custom event"));
        return "Hi";
    }

}
