package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;


public interface IClassService {

	Classes create(Classes classes);

	Classes update(Classes classes);

	//void delete(int id);
	  
	Optional<Classes> listId(int id);

	List<Classes> list();

	public List<Classes> recycleBin(); 

	public void softDelete(int id);
	
	List<Students> listStudentByClass(int classId);
}
