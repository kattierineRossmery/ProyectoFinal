package com.everis.ejercicio1.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio1.models.Students;

public interface IStudentsDAO extends CrudRepository<Students, Integer> {
	
	/**
	 * Metodo Query para obtener una lista de Students dado una lista de studentsId.
	 * 
	 * @param listStudentId se ingresa los id de estudiantes a mostrar.
	 * @return lista.
	 */
	@Query("select s from Students s WHERE s.studentId IN :listStudentId")
	  List<Students> getAllStudentsByStudentId(List<Integer> listStudentId);

	/**
	 * Metodo Query
	 * Sirven para la eliminación lógica o softDelete.
	 * false = no eliminado y true = eliminado.
	 * 
	 * Override CrudRepository or PagingAndSortingRepository's query method.
	 */
		@Override
		@Query("select e from Students e where e.deleted=false")
		@Transactional
		public List<Students> findAll();

		//Look up deleted entities
		@Query("select e from Students e where e.deleted=true")
		@Transactional
		public List<Students> recycleBin(); 

		//Soft delete.
		@Query("update Students e set e.deleted=true where e.studentId=?1")
		@Transactional
		@Modifying
		public void softDelete(int id);
		
	
}
