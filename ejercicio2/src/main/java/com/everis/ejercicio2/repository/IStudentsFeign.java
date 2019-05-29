package com.everis.ejercicio2.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.everis.ejercicio2.models.Students;

@FeignClient(name="ejercicio1-service", url="https://localhost:2222")
public interface IStudentsFeign {
	
	  
	  @RequestMapping(value = "/api/v1/students")
	  List<Object> getList();
	  
	  @RequestMapping(value = "/api/v1/students/{id}")
	  Object getListId(@PathVariable("id") int id);
	  
	  @RequestMapping(value = "/api/v1/students/ids", method = RequestMethod.POST)
	  List<Students> listStudentsByStudentId(List<Integer> listStudentId);
	
}
