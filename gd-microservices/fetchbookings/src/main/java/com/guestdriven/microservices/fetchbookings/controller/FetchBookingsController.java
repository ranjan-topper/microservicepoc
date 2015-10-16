package com.guestdriven.microservices.fetchbookings.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guestdriven.core.microservices.model.DELMessage;
import com.guestdriven.core.microservices.model.DELMessageAcknowledgement;
import com.guestdriven.core.util.ServiceUtils;

/**
 * Fetch Bookings controller
 * 
 * @author ZapEmp
 */

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController
public class FetchBookingsController {
	private static final Logger LOG = LoggerFactory.getLogger(FetchBookingsController.class);
	@Autowired
	ServiceUtils util;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<DELMessageAcknowledgement> fetchBookings(@RequestBody DELMessage message) {
		LOG.info("FetchBookingsController.fetchBookings() -> Start");
		LOG.info("FetchBookingsController.fetchBookings()-> DelMessage = {}", message.getPayload());
		return util.createOkResponse(new DELMessageAcknowledgement(message.getId(), "Received", message.getPayload()));
	}
}
