package com.everis.ejercicio2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.ejercicio2.models.StudentsClasses;

public interface IClassStudentsDAO extends CrudRepository<StudentsClasses, Integer>{

	@Query(value = "select sc.students.studentId from StudentsClasses sc WHERE sc.classes.classId=?1")
	List<Integer> findByClassesClassId(Integer classId);
}
