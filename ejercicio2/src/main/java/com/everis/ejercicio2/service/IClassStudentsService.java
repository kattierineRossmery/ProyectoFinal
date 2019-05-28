package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio2.models.StudentsClasses;

public interface IClassStudentsService {

	StudentsClasses create(StudentsClasses classesSt);

	StudentsClasses update(StudentsClasses classesSt);

	void delete(int id);
	  
	Optional<StudentsClasses> listId(int id);

	List<StudentsClasses> list();
	
}
