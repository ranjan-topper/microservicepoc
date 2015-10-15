package com.guestdriven.microservices.checkin.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guestdriven.core.microservices.model.DELMessage;
import com.guestdriven.core.microservices.model.DELMessageAcknowledgement;
import com.guestdriven.core.util.ServiceUtils;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController
public class CheckinController {

	@Autowired
	ServiceUtils util;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<DELMessageAcknowledgement> sendMessage(@RequestBody DELMessage message) {
		System.out.println("CheckinController.sendMessage()->");
		//util.createOkResponse(body)
		//DELMessageAcknowledgement response = new DELMessageAcknowledgement(message.getId(), "Received", message.getPayload());
		return util.createResponse(new DELMessageAcknowledgement(message.getId(), "Received", message.getPayload()), HttpStatus.OK);
	}
}
