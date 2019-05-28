package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.ejercicio2.models.StudentsClasses;
import com.everis.ejercicio2.repository.IClassStudentsDAO;

@Service
public class StudentsClassesImpl implements IClassStudentsService{
	
	@Autowired
	IClassStudentsDAO repo;

	@Override
	public StudentsClasses create(StudentsClasses classesSt) {
		return repo.save(classesSt);
	}

	@Override
	public StudentsClasses update(StudentsClasses classesSt) {
		return repo.save(classesSt);
	}

	@Override
	public void delete(int id) {
			repo.deleteById(id);		
	}

	@Override
	public Optional<StudentsClasses> listId(int id) {
		return repo.findById(id);
	}

	@Override
	public List<StudentsClasses> list() {
		return (List<StudentsClasses>) repo.findAll();
	}

}
