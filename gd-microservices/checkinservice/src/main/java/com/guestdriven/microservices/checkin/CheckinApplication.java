package com.guestdriven.microservices.checkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.guestdriven.microservices.checkin.controller.CheckinController;


@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.guestdriven.microservices.checkin","com.guestdriven.core.util"})
@Import(CheckinController.class)
public class CheckinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckinApplication.class, args);
    }
}
