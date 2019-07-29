package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCircuitBreaker
@EnableDiscoveryClient
@ComponentScan
@Configuration
public class SpringBootHelloWorldApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootHelloWorldApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
	
	    @Override
	    public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").
			  addResourceLocations("classpath:/static/test-app/");
		//.addResourceLocations("file:/opt/files/"); for external files “file:///C:/opt/files/“). http://www.baeldung.com/spring-mvc-static-resources
	    }
	        
	    }
}
