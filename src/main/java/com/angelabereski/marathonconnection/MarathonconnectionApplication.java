package com.angelabereski.marathonconnection;

import javax.annotation.Resource;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import com.angelabereski.marathonconnection.services.FilesService;

@SpringBootApplication
public class MarathonconnectionApplication implements CommandLineRunner {

	@Resource
	FilesService filesService;

	public static void main(String[] args) {
		SpringApplication.run(MarathonconnectionApplication.class, args);
	}

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		Connector ajpConnector = new Connector("AJP/1.3");
		ajpConnector.setPort(9090);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("http");
		((AbstractAjpProtocol<?>) ajpConnector.getProtocolHandler()).setSecretRequired(false);
		tomcat.addAdditionalTomcatConnectors(ajpConnector);
		return tomcat;
	}

	@Override
	public void run(String... arg) throws Exception {
		this.filesService.init();
	}

}
