package com.example.serki;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SerkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerkiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(WorkshopsService workshopsService){
        return args -> {
            workshopsService.addWorkshop(new WorkshopsDTO("IT", "bleblebleb"));
            workshopsService.addWorkshop(new WorkshopsDTO("MARKETING", "WFAFWS"));
        };
    }
}
