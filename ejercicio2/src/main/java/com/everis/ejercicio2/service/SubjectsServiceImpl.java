package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.repository.ISubjectDAO;

@Service
public class SubjectsServiceImpl implements ISubjectService{

	@Autowired
	private ISubjectDAO repo;
	
	@Override
	public Subjects create(Subjects subject) {
		return repo.save(subject);
	}

	@Override
	public Subjects update(Subjects subject) {
		return repo.save(subject);
	}


	@Override
	public Optional<Subjects> listId(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Subjects> list() {
		return repo.findAll();
	}

	@Override
	public List<Subjects> recycleBin() {
		return repo.recycleBin();
	}

	@Override
	public void softDelete(int id) {
		repo.softDelete(id);
	}


}
