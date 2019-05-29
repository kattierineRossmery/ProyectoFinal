package com.everis.ejercicio2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;

import feign.Param;

public interface IClassDAO extends CrudRepository<Classes, Integer>{
	
	@Query("select c.classId, s.studentId,s.firstName, s.middleName, s.lastName from Classes c inner join StudentsClasses sc \r\n" + 
			"on c.classId= sc.classes.classId inner join Students s \r\n" + 
			"on s.studentId=sc.students.studentId \r\n" + 
			"where c.classId =:classId")
	List<Students> listStudentByClass(@Param("classId") Integer classId);

	 //Override CrudRepository or PagingAndSortingRepository's query method:
			// false = no eliminado y true = eliminado
				@Override
				@Query("select e from Classes e where e.deleted=false")
				@Transactional
				public List<Classes> findAll();

				//Look up deleted entities
				@Query("select e from Classes e where e.deleted=true")
				@Transactional
				public List<Classes> recycleBin(); 

				//Soft delete.
				@Query("update Classes e set e.deleted=true where e.classId=?1")
				@Transactional
				@Modifying
				public void softDelete(int id);
}
