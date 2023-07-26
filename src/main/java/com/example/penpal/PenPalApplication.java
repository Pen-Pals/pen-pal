package com.example.penpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PenPalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PenPalApplication.class, args);
    }

}
