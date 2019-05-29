package com.everis.ejercicio1.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Families;

/**
 * Repositoryo de Families
 * conocido como DAO.
 * @author kvilcave
 *
 */
public interface IFamiliesDAO extends CrudRepository<Families, Integer> {
  
	//Override CrudRepository or PagingAndSortingRepository's query method:
	// false = no eliminado y true = eliminado
		@Override
		@Query("select e from Families e where e.deleted=false")
		@Transactional
		public List<Families> findAll();

		//Look up deleted entities
		@Query("select e from Families e where e.deleted=true")
		@Transactional
		public List<Families> recycleBin(); 

		//Soft delete.
		@Query("update Families e set e.deleted=true where e.familyId=?1")
		@Transactional
		@Modifying
		public void softDelete(int id);

}
