package com.guestdriven.microservices.fetchbookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.guestdriven.microservices.fetchbookings"})
public class FetchBookingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchBookingsApplication.class, args);
    }
}
