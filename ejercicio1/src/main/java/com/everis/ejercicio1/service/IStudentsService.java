package com.everis.ejercicio1.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio1.models.Students;


public interface IStudentsService {

  Students create(Students students);

  Students update(Students students);

  void delete(int id);
  
  Optional<Students> listId(int id);

  List<Students> list();

}
