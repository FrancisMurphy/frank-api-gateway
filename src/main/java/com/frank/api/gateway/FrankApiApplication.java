package com.frank.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author frank
 */
@SpringBootApplication
@EnableWebFlux
public class FrankApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(FrankApiApplication.class, args);
    }

}
