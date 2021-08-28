package com.brq.aviaoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AviaoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AviaoMsApplication.class, args);
	}

}
