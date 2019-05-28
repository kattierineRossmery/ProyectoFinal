package com.everis.ejercicio1.service;

import com.everis.ejercicio1.models.Students;
import com.everis.ejercicio1.repository.IStudentsDAO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentsServiceImpl implements IStudentsService {

  @Autowired
  private IStudentsDAO repo;

  @Override
  public Students create(Students students) {
    return repo.save(students);
  }

  @Override
  public Students update(Students students) {
    return repo.save(students);
  }

  @Override
  public void delete(int id) {
    repo.deleteById(id);
  }

  @Override
  public List<Students> list() {
    return (List<Students>) repo.findAll();
  }

  @Override
  public Optional<Students> listId(int id) {
    return repo.findById(id);
  }

}
