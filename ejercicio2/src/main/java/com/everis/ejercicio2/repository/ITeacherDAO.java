package com.everis.ejercicio2.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.Teachers;

public interface ITeacherDAO extends CrudRepository<Teachers, Integer>{

}
