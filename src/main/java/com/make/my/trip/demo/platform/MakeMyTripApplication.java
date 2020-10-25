package com.make.my.trip.demo.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com")
public class MakeMyTripApplication {

    public static void main(String[] args) {

        SpringApplication.run(MakeMyTripApplication.class, args);
    }

}
