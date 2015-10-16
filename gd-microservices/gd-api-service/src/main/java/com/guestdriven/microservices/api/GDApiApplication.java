package com.guestdriven.microservices.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.guestdriven.microservice.api.controller.GDDelApiService;

/**
 * GDApi microservice class
 * 
 * @author ZapEmp
 **/
@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@ComponentScan({ "com.guestdriven.microservices.api", "com.guestdriven.core.util" })
@Import(GDDelApiService.class)
public class GDApiApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GDApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GDApiApplication.class, args);
		LOG.info("GDApiApplication -> GD API Micro service started.");
	}
}
