package com.example.scope.demo.clr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class myClr implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info(" &&& My Command line runner &&&");
    }
}
