package com.example.scope.demo.event;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {

    private Logger log = LoggerFactory.getLogger(CustomSpringEventPublisher.class.getName());

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(ApplicationEvent event) {
        log.info("*** Publishing custom event. ***");
        applicationEventPublisher.publishEvent(event);
    }

}
