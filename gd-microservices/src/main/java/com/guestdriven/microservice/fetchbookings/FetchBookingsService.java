package com.guestdriven.microservice.fetchbookings;
/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ratpack.jackson.JacksonModule;
import ratpack.server.RatpackServer;

/**
 * Quick test app to verify a sample in Ractpack documentation.
 * 
 *
 */
@Configuration
@EnableAutoConfiguration
public class FetchBookingsService {
	
	Logger logger = LoggerFactory.getLogger(FetchBookingsService.class);

	/*@Bean	
	public Action<Chain> home() {
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("****************************Fetch Bookings service***************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");
		System.out.println("*****************************************************************************************");

		return chain -> chain.prefix("guestdriven/checkin", pchain -> pchain
		.post(ctx -> {
			String xml = ctx.getRequest().getBody().getText();
			ctx.render("Recieved post request--->" + xml);
		})

		.get(ctx -> {
			ctx.render("Received get request");
		})
		);
		

		return chain -> chain.prefix("guestdriven/fetchcurrentbooking", pchain -> pchain.all(ctx -> {
			ctx.byMethod(method -> method.post(() -> {
				logger.info("FetchBookingsService.home()-> FetchBookingsService request received");
				String xml = ctx.getRequest().getBody().getText();
				ctx.render("Recieved post request--->" + xml);
			})
			.get(() -> {
				ctx.render("Received get request");
			}));
		}));
	}*/

	@Bean
	public JacksonModule jacksonModule() {
		return new JacksonModule();
	}

	@Bean
	public Service service() {
		return () -> "World";
	}

	public static void main(String... args) throws Exception {
		// Tell server to look for fetchbookings-server.properties or
		 //checkin-server.yml
		/*System.setProperty("spring.config.name", "fetchbookings-server");
		SpringApplication.run(FetchBookingsService.class, args);*/
		
		 RatpackServer.start(server -> server
			     .registryOf(registry -> registry.add("fetchbookings"))
			     .handlers(chain -> chain.prefix("guestdriven/fetchcurrentbooking", pchain -> pchain.all(ctx -> {
						ctx.byMethod(method -> method.post(() -> {
							System.out.println("FetchBookingsService.home()-> FetchBookingsService request received");
							String xml = ctx.getRequest().getBody().getText();
							ctx.render("Recieved post request--->" + xml);
						})
						.get(() -> {
							ctx.render("Received get request");
						}));
					}))));
	}

}

interface Service {
	String message();
}
