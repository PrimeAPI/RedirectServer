package de.primeapi.gowebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoWebsiteApplication {


    public static final String PASSWORD = "FooBar";

    public static void main(String[] args) {
        SpringApplication.run(GoWebsiteApplication.class, args);
    }

}
