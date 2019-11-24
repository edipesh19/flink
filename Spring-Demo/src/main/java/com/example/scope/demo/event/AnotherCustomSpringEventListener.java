package com.example.scope.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnotherCustomSpringEventListener  implements ApplicationListener<AnotherCustomSpringEvent> {

    @Override
    public void onApplicationEvent(AnotherCustomSpringEvent event) {
        log.info("*** Received AnotherCustomSpringEvent - {} ***", event.getMessage());
    }
}
