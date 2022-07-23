package com.example.serki;

import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.service.WorkshopsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SerkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerkiApplication.class, args);
    }

}
