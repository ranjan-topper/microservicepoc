package com.guestdriven.microservices.fetchbookings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.guestdriven.microservices.fetchbookings.controller.FetchBookingsController;

/**
 * Fetch Bookings micro service
 * 
 * @author ZapEmp
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({ "com.guestdriven.microservices.fetchbookings", "com.guestdriven.core.util" })
@Import(FetchBookingsController.class)
public class FetchBookingsApplication {

	private static final Logger LOG = LoggerFactory.getLogger(FetchBookingsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FetchBookingsApplication.class, args);
		LOG.info("FetchBookingsApplication -> FetchBooking Micro service started.");
	}
}
