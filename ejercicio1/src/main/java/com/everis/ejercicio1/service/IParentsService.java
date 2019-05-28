package com.everis.ejercicio1.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio1.models.Parents;


public interface IParentsService {

  Parents create(Parents parents);

  Parents update(Parents parents);

  void delete(int id);
  
  Optional<Parents> listId(int id);

  List<Parents> list();
}
