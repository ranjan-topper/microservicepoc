package com.guestdriven.microservices.checkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.guestdriven.microservices.checkin.controller.CheckinController;

/**
 * Checkin micro service
 * 
 * @author ZapEmp
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({ "com.guestdriven.microservices.checkin", "com.guestdriven.core.util" })
@Import(CheckinController.class)
public class CheckinApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CheckinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CheckinApplication.class, args);
		LOG.info("CheckinApplication -> Checkin Micro service started.");
	}
}
