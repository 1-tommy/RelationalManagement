package com;

import jakarta.websocket.ClientEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
public class RelationalManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationalManagementApplication.class, args);
    }

}
