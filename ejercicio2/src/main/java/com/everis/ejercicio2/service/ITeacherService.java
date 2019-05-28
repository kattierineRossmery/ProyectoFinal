package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio2.models.Teachers;

public interface ITeacherService {
	
	Teachers create(Teachers teacher);

	Teachers update(Teachers teacher);

	//void delete(int id);
	  
	Optional<Teachers> listId(int id);

	List<Teachers> list();

	public List<Teachers> recycleBin(); 

	public void softDelete(int id);
}
