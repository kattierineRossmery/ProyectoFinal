package com.everis.ejercicio1.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Students;

public interface IStudentsDAO extends CrudRepository<Students, Integer> {

}
