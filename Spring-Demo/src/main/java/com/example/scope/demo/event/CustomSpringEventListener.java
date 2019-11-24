package com.example.scope.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    private Logger log = LoggerFactory.getLogger(CustomSpringEventListener.class.getName());

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info(" *** Received CustomSpringEvent - {} *** ", event.getMessage());
    }
}
