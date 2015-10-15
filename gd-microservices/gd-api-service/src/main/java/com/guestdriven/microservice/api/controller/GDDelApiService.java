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
import org.springframework.http.HttpStatus;
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
 * Created by magnus on 04/03/15.
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

    @RequestMapping(value="/checkin", method=RequestMethod.POST)
   // @HystrixCommand(fallbackMethod = "defaultCheckin")
    public ResponseEntity<DELMessageAcknowledgement> checkin(@RequestBody DELMessage delMessage) {

       /* MDC.put("productId", productId);
        LOG.info("ProductApi: User={}, Auth={}, called with productId={}", currentUser.getName(), authorizationHeader, productId);*/
    	LOG.info("GDDelApiService.checkin() -> Start");
    	//URI uri = util.getServiceUrl("checkin");
    	
    	//String url = uri.toString() + "/checkin";
        // LOG.debug("Checkin service URL: {}", url);

       // URI uri = loadBalancer.choose("checkin").getUri();
       // String url = uri.toString() + "/checkin";
       // LOG.debug("checkin from URL: {}", uri);
        HttpEntity<DELMessage> inputEntity = new HttpEntity<DELMessage>(delMessage, null);
        ResponseEntity<DELMessageAcknowledgement> result = restTemplate.exchange("http://CHECKIN", HttpMethod.POST, inputEntity, DELMessageAcknowledgement.class);
       // ResponseEntity<DELMessageAcknowledgement> result = restTemplate.postForEntity("http://checkin", delMessage, DELMessageAcknowledgement.class, null);
        		//getForEntity("http://checkin", Message.class);
        LOG.info("GDDelApiService.checkin() -> Start http-status: {}", result.getStatusCode());
        LOG.debug("GetProductComposite body: {}", result.getBody());

        return util.createResponse(result);
    }

    /**
     * Fallback method for checkin()
     *
     * @param productId
     * @return
     */
    public ResponseEntity<String> defaultCheckin(@RequestBody DELMessage delMessage) {

        //LOG.warn("Using fallback method for product-composite-service. User={}, Auth={}, called with productId={}", currentUser.getName(), authorizationHeader, productId);
    	LOG.error("defaultCheckin();"+delMessage.getId());
        return new ResponseEntity<String>("", HttpStatus.BAD_GATEWAY);
    }
}
