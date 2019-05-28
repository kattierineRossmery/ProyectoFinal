package com.everis.ejercicio1.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Families;

/**
 * Repositoryo de Families
 * conocido como DAO.
 * @author kvilcave
 *
 */
public interface IFamiliesDAO extends CrudRepository<Families, Integer> {
  
  

}
