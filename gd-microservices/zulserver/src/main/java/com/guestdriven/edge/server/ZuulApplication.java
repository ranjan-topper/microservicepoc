package com.guestdriven.edge.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import io.undertow.servlet.api.DeploymentInfo;

/**
 * API Gateway micro service
 * 
 * @author ZapEmp
 */

@SpringBootApplication
@Controller
@EnableZuulProxy
public class ZuulApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ZuulApplication.class);
    public static void main(String[] args) {
    	LOG.info("CheckinApplication -> API Gateway (Zuul) Micro service started.");
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
    
    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addDeploymentInfoCustomizers(new UndertowDeploymentInfoCustomizer() {
            @Override
            public void customize(DeploymentInfo deploymentInfo) {
                deploymentInfo.setAllowNonStandardWrappers(true);
            }
        });
        return factory;
    }

}
