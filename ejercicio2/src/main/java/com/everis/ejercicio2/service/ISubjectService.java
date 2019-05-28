package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio2.models.Subjects;

public interface ISubjectService {

	Subjects create(Subjects subject);

	Subjects update(Subjects subject);

	//void delete(int id);
	  
	Optional<Subjects> listId(int id);

	List<Subjects> list();

	public List<Subjects> recycleBin(); 

	public void softDelete(int id); 
	
}
