package com.everis.ejercicio1.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Parents;

public interface IParentsDAO extends CrudRepository<Parents, Integer> {

}
