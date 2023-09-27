package com.bankproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankProjectApplication.class, args);
    }

}
