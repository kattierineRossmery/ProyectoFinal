package com.everis.ejercicio1.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Parents;

public interface IParentsDAO extends CrudRepository<Parents, Integer> {

	//Override CrudRepository or PagingAndSortingRepository's query method:
		// false = no eliminado y true = eliminado
			@Override
			@Query("select e from Parents e where e.deleted=false")
			@Transactional
			public List<Parents> findAll();

			//Look up deleted entities
			@Query("select e from Parents e where e.deleted=true")
			@Transactional
			public List<Parents> recycleBin(); 

			//Soft delete.
			@Query("update Parents e set e.deleted=true where e.parentId=?1")
			@Transactional
			@Modifying
			public void softDelete(int id);
}
