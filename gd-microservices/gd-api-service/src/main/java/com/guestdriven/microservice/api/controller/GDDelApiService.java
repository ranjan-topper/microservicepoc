package com.guestdriven.microservice.api.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.guestdriven.core.microservices.model.DELMessage;
import com.guestdriven.core.microservices.model.DELMessageAcknowledgement;
import com.guestdriven.core.util.ServiceUtils;

/**
 * GDAPI class can be used to integrate multiple microservices
 * 
 * @author ZapEmp
 */
@RestController
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class GDDelApiService {

	private static final Logger LOG = LoggerFactory.getLogger(GDDelApiService.class);

	@Autowired
	ServiceUtils util;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@RequestMapping(value = "/guestdriven/checkin", method = RequestMethod.POST)
	// @HystrixCommand(fallbackMethod = "defaultCheckin")
	public ResponseEntity<DELMessageAcknowledgement> checkin(@RequestBody DELMessage delMessage) {

		LOG.info("GDDelApiService.checkin() -> Start");
		/* URI uri = util.getServiceUrl("checkin");
		 String url = uri.toString() + "/checkin";
		LOG.info("Checkin service URL: {}", url);*/
		HttpEntity<DELMessage> inputEntity = new HttpEntity<DELMessage>(delMessage, null);
		ResponseEntity<DELMessageAcknowledgement> result = restTemplate.exchange("http://CHECKIN", HttpMethod.POST,
				inputEntity, DELMessageAcknowledgement.class);
		/*ResponseEntity<DELMessageAcknowledgement> result = restTemplate.exchange(url, HttpMethod.POST,
				inputEntity, DELMessageAcknowledgement.class);*/
		LOG.info("GDDelApiService.checkin() -> Start http-status: {}", result.getStatusCode());
		LOG.debug("Checkin response body: {}", result.getBody());
		LOG.debug("GDDelApiService.checkin() -> End");
		return util.createResponse(result);
	}

	@RequestMapping(value = "/guestdriven/fetchBookings", method = RequestMethod.POST)
	// @HystrixCommand(fallbackMethod = "defaultCheckin")
	public ResponseEntity<DELMessageAcknowledgement> fetchBookings(@RequestBody DELMessage delMessage) {

		LOG.info("GDDelApiService.fetchBookings() -> Start");
		HttpEntity<DELMessage> inputEntity = new HttpEntity<DELMessage>(delMessage, null);
		ResponseEntity<DELMessageAcknowledgement> result = restTemplate.exchange("http://FETCHBOOKINGS", HttpMethod.POST,
				inputEntity, DELMessageAcknowledgement.class);
		/*ResponseEntity<DELMessageAcknowledgement> result = restTemplate.exchange(url, HttpMethod.POST,
				inputEntity, DELMessageAcknowledgement.class);*/
		LOG.info("GDDelApiService.fetchBookings() -> Start http-status: {}", result.getStatusCode());
		LOG.debug("Fetch Bookings response body: {}", result.getBody());
		LOG.debug("GDDelApiService.fetchBookings() -> End");
		return util.createResponse(result);
	}

}
