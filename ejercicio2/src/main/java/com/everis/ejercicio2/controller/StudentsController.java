package com.everis.ejercicio2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.repository.IStudentsFeign;

@RestController
public class StudentsController {
	
	IStudentsFeign res;

	@Autowired
	public StudentsController(IStudentsFeign res) {
	
		this.res = res;
	}

	@GetMapping("/api/v2/stu")
	public List<Object> getList() throws InterruptedException {
		
		return res.getList();
	}
	
	@GetMapping("/api/v2/stu/{id}")
	Object getListId(@PathVariable("id") int id) throws InterruptedException{
		return res.getListId(id);
	}
	
	@RequestMapping("/api/v2/stu/ids")
	public List<Students> listStudentsByStudentId(@RequestBody List<Integer> listStudentId) throws InterruptedException{
		  return res.listStudentsByStudentId(listStudentId);
	}

	
}
