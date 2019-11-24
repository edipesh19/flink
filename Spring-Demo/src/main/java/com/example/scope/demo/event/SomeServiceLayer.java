package com.example.scope.demo.event;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceLayer {
    private static final Logger log = LoggerFactory.getLogger(SomeServiceLayer.class.getName());

    @EventListener
    public void eventListner(CustomSpringEvent event){
        log.info("### Event received - {} ###", event.getMessage());
    }

    // Listen all the events including spring framework events also
    @EventListener
    public void eventListner(ApplicationEvent event) {
        log.info("!!! Event received - {} !!!", event.getClass().getName());
    }

}
