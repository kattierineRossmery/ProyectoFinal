package com.everis.ejercicio2.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.everis.ejercicio2.models.Students;

/**
 * Esta interfaz sirve para consumir servicios de Students
 * perteneciente al proyecto ejercicio1-service.
 * 
 * @author kvilcave
 *
 */
@FeignClient(name="ejercicio1-service", url="https://localhost:2222")
public interface IStudentsFeign {
	
	  /**
	   * listar todos los estudiantes
	   * @return
	   */
	  @RequestMapping(value = "/api/v1/students")
	  List<Object> getList();
	  
	  /**
	   * listar por id al estudiante
	   * @param id id
	   * @return objecto
	   */
	  @RequestMapping(value = "/api/v1/students/{id}")
	  Object getListId(@PathVariable("id") int id);
	  
	  /**
	   * listar estudiantes por una lista de ids.
	   * @param listStudentId listStudentId
	   * @return lista
	   */
	  @RequestMapping(value = "/api/v1/students/ids", method = RequestMethod.POST)
	  List<Students> listStudentsByStudentId(List<Integer> listStudentId);
	
}
