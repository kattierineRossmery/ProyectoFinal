package com.everis.ejercicio2.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.StudentsClasses;

public interface IClassStudentsDAO extends CrudRepository<StudentsClasses, Integer>{

}
