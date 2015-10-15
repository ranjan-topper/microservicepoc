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

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@ComponentScan({"com.guestdriven.microservices.api","com.guestdriven.core.util"})
@Import(GDDelApiService.class)
public class GDApiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GDApiApplication.class);

    static {
        // for localhost testing only
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
       // HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    public static void main(String[] args) {
        LOG.info("Register MDCHystrixConcurrencyStrategy");
        //HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
        SpringApplication.run(GDApiApplication.class, args);
    }
}
