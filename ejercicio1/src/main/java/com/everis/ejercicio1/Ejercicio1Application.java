package com.everis.ejercicio1;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration //defines this as a Spring Boot application
@EnableDiscoveryClient //this enables service registration and discovery. In this case, this process registers itself with the discovery-server service using its application name 
@SpringBootApplication
public class Ejercicio1Application {
	protected Logger logger = Logger.getLogger(Ejercicio1Application.class.getName());

  public static void main(String[] args) {
    SpringApplication.run(Ejercicio1Application.class, args);
  
  
    /**
     * AGREGAR JAVA DOC EN TODO EL PROYECTO
     */
  }

}
