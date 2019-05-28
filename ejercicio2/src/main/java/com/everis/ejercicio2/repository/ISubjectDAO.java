package com.everis.ejercicio2.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.Subjects;

public interface ISubjectDAO extends CrudRepository<Subjects, Integer>{

}
