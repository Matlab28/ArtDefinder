package com.example.artdefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ArtDefinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtDefinderApplication.class, args);
    }

}
