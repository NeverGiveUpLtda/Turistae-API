package com.api.turistae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class TuristaeApplication {

    // Logger do projeto
    private static final Logger logger = LoggerFactory.getLogger(TuristaeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TuristaeApplication.class, args);
        logger.info("Boot finalizado. Ouvindo porta 8080");
    }
}
