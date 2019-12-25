package com.example.scope.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Bean
    public ExitCodeGenerator exitCodeGenerator(){
        return ()->42;
    }


    /*public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
    }*/

    public static void main(String[] args) {
        logger.info("*** STARTED ***");
        //System.exit(SpringApplication.exit(SpringApplication.run(DemoApplication.class, args)));
        SpringApplication.run(DemoApplication.class, args);
    }

    // Localization -> i10n
//    @Bean
//    public LocaleResolver localResolver(){
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.US);
//        return localeResolver;
//
//    }
//
//    @Bean
//    public ResourceBundleMessageSource bundleMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }

}
