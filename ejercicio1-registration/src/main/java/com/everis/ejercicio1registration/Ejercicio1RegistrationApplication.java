package com.everis.ejercicio1registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Ejercicio1RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1RegistrationApplication.class, args);
	}

}
