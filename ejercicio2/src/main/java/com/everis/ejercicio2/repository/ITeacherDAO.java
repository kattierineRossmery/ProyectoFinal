package com.everis.ejercicio2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.Teachers;

public interface ITeacherDAO extends CrudRepository<Teachers, Integer>{

	//Override CrudRepository or PagingAndSortingRepository's query method:
			// false = no eliminado y true = eliminado
				@Override
				@Query("select e from Teachers e where e.deleted=false")
				@Transactional
				public List<Teachers> findAll();

				//Look up deleted entities
				@Query("select e from Teachers e where e.deleted=true")
				@Transactional
				public List<Teachers> recycleBin(); 

				//Soft delete.
				@Query("update Teachers e set e.deleted=true where e.teachersId=?1")
				@Transactional
				@Modifying
				public void softDelete(int id);	
}
