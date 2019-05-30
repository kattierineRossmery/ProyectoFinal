package com.everis.ejercicio2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.repository.IStudentsFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class StudentsController {
	
	IStudentsFeign res;

	@Autowired
	public StudentsController(IStudentsFeign res) {
	
		this.res = res;
	}

	@GetMapping("/api/v2/stu")
	@HystrixCommand(fallbackMethod = "fallback",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	}, commandKey = "stu", groupKey = "stu")
	public List<Object> getList() throws InterruptedException {
		Thread.sleep(800);
		return res.getList();
	}
	
	@GetMapping("/api/v2/stu/{id}")
	Object getListId(@PathVariable("id") int id) throws InterruptedException{
		//Thread.sleep(800);
		return res.getListId(id);
	}
	
	@RequestMapping("/api/v2/stu/ids")
	public List<Students> listStudentsByStudentId(@RequestBody List<Integer> listStudentId) throws InterruptedException{
		return res.listStudentsByStudentId(listStudentId);
	}

	public List<Object> fallback() {
		List<Object> error = new ArrayList<Object>();
		error.add("Espere un momento por favor");
		return error;
	}
}
