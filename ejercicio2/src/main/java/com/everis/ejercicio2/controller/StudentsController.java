package com.everis.ejercicio2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.ejercicio2.repository.IStudentsFeign;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {
	@Autowired
	IStudentsFeign res;
	
	@GetMapping("/stu")
	public List<Object> getList() throws InterruptedException {
		
		return res.getList();
	}
	@GetMapping("/stu/{id}")
	public List<Object> getListXid() throws InterruptedException {
		
		return res.getListId();
	}

	
	
}
