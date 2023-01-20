package com.example.embedika_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmbedikaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbedikaTestApplication.class, args);
    }

}
