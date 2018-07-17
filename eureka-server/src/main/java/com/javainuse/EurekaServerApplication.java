package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(scanBasePackages={"com.javainuse"})
@EnableEurekaServer
public class EurekaServerApplication extends SpringBootServletInitializer{

	/**
	 * war and jar package compatible due to the SpringApplicationBuilder configure()
	 * function, it is a call back method, call by the container of Servers like Tomcat
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EurekaServerApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
