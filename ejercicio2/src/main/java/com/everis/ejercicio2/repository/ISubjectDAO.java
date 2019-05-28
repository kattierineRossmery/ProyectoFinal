package com.everis.ejercicio2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.Subjects;

public interface ISubjectDAO extends CrudRepository<Subjects, Integer>{

	//Override CrudRepository or PagingAndSortingRepository's query method:
		// false = no eliminado y true = eliminado
			@Override
			@Query("select e from Subjects e where e.deleted=false")
			@Transactional
			public List<Subjects> findAll();

			//Look up deleted entities
			@Query("select e from Subjects e where e.deleted=true")
			@Transactional
			public List<Subjects> recycleBin(); 

			//Soft delete.
			@Query("update Subjects e set e.deleted=true where e.subjectId=?1")
			@Transactional
			@Modifying
			public void softDelete(int id); 	
}
