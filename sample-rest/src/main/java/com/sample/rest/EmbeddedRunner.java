package com.sample.rest;

import com.sample.rest.config.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import({ // @formatter:off
        PersistenceConfig.class
// @formatter:on
})

public class EmbeddedRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedRunner.class, args);
        System.out.println("Running now changed..");
    }

}
