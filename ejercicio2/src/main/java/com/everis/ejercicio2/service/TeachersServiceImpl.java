package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.ejercicio2.models.Teachers;
import com.everis.ejercicio2.repository.ITeacherDAO;

@Service
public class TeachersServiceImpl implements ITeacherService{

	@Autowired
	private ITeacherDAO repo;
	
	@Override
	public Teachers create(Teachers teacher) {
		
		
		
		return repo.save(teacher);
	}

	@Override
	public Teachers update(Teachers teacher) {
		return repo.save(teacher);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}


	@Override
	public List<Teachers> list() {
		return (List<Teachers>) repo.findAll();
	}

	@Override
	public Optional<Teachers> listId(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}


}
