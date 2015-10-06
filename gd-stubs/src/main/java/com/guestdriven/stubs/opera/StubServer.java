package com.guestdriven.stubs.opera;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.io.CharStreams;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.spring.config.EnableRatpack;

/**
 * Stub application class which handles below http requests and provides the
 * stubbed responses. It handles the following requests.
 * 
 * fetchBooking, fetchAvailableOffers, upsellReservation, fetchRoomUpgrades,
 * upgradeReservation, modifyBooking and updatePackages
 * 
 * @author gurrams
 */
@Configuration
@EnableAutoConfiguration
@EnableRatpack
@PropertySource("classpath:application.properties")
public class StubServer {

	Logger logger = LoggerFactory.getLogger(StubServer.class);

	@Bean
	public Action<Chain> home() {
		logger.info("*******************************************************************");
		logger.info("*******************************************************************");
		logger.info("*****************Guest Driven Stubs service************************");
		logger.info("*******************************************************************");
		logger.info("*******************************************************************");

		return chain -> chain.prefix("guestdriven/stubs/opera",
				pchain -> pchain.prefix(":requestName", uchain -> uchain.all(ctx -> {
					ctx.byMethod(method -> method.post(() -> {
						String requestName = ctx.getPathTokens().get("requestName");
						logger.info("StubServer.home()-> request received for: " + requestName);
						// String xml = ctx.getRequest().getBody().getText();
						String response = stubService().getStubbedData(requestName);
						ctx.render(response);
					}).get(() -> {
						String requestName = ctx.getPathTokens().get("requestName");
						logger.info("StubServer.home()-> request received for: " + requestName);
						// String xml = ctx.getRequest().getBody().getText();
						String response = stubService().getStubbedData(requestName);
						ctx.render(response);
					}));
				})));
	}

	@Bean
	public StubBindingService stubService() {

		return new StubBindingService() {

			@Autowired
			private ResourceLoader resourceLoader;

			@Inject
			private Environment env;

			@Override
			public String getStubbedData(String request) {
				logger.info("StubBindingService.getStubbedData()->: processing request:" + request);
				String filename = env.getProperty(request);
				String data = (filename != null) ? this.readStubbedData(filename)
						: "Given request name not supported - " + request;
				logger.info("StubBindingService.getStubbedData()->:" + request+ "completed");
				return data;
			}

			private String readStubbedData(String fileName) {
				logger.info("Reading data from:" + fileName);
				String data = null;
				Resource resource = resourceLoader.getResource("classpath:" + fileName);
				try {
					InputStream stream = resource.getInputStream();
					data = CharStreams.toString(new InputStreamReader(stream, "UTF-8"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				logger.info("Response Data is:" + data);
				return data;
			}
		};
	}

}

interface StubBindingService {
	String getStubbedData(String request);
}
