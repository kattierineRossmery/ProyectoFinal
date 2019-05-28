package com.everis.ejercicio2.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="ejercicio1-service", url="https://localhost:2222")
public interface IStudentsFeign {
	
		
	  @RequestMapping(value = "/api/v1/students")
	  List<Object> getList();
	  
	  @RequestMapping(value = "/api/v1/students/{id}")
	  List<Object> getListId();
	
	

}
