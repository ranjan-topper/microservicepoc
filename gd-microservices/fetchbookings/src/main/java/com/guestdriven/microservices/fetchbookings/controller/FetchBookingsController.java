package com.guestdriven.microservices.fetchbookings.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guestdriven.microservices.model.Message;
import com.guestdriven.microservices.model.MessageAcknowledgement;

@RestController
public class FetchBookingsController {
    @RequestMapping(value="guestdriven/fetchbookings", method= RequestMethod.POST)
    public @ResponseBody MessageAcknowledgement sendMessage(@RequestBody Message message) {
    	System.out.println("FetchBookingsController.sendMessage()->");
    	 return new MessageAcknowledgement(message.getId(), "Received", message.getPayload());
    }
}
