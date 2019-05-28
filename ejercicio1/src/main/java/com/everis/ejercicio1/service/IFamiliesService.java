package com.everis.ejercicio1.service;

import java.util.List;
import java.util.Optional;

import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.FamilyMembers;

public interface IFamiliesService {

  Families create(Families families);

  public void delete(int id);

  Optional<Families> listId(int id);

  List<Families> list();
  
  List<FamilyMembers> findByFamiliesFamily_id(int familyId);

  public Families update(Families families);



}
