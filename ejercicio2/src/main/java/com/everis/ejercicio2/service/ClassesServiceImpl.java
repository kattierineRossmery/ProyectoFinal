package com.everis.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.ejercicio2.exception.ModeloNotFoundException;
import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.models.Teachers;
import com.everis.ejercicio2.repository.IClassDAO;

@Service
public class ClassesServiceImpl implements IClassService{

	@Autowired
	private IClassDAO repo;
	
	@Autowired
	private ISubjectService servSub;
	
	@Autowired
	private ITeacherService servTea;
	
	@Override
	public Classes create(Classes classes) {
		Subjects s = new Subjects();
		Optional<Subjects> objS = servSub.listId(s.getSubjectId());
		Teachers t = new Teachers();
		Optional<Teachers> objT = servTea.listId(t.getTeachersId());
		
		if(!objS.isPresent()) {
			String mensaje = "ID-"+classes.getSubjects().getSubjectId()+ " no existe";
		throw new ModeloNotFoundException(mensaje);
		}
		if(!objT.isPresent()) {
			String mensaje2 = "ID-"+classes.getTeachers().getTeachersId()+" no existe";
		throw new ModeloNotFoundException(mensaje2);
		}
		
		return repo.save(classes);
	}

	@Override
	public Classes update(Classes classes) {
		return repo.save(classes);
	}


	@Override
	public Optional<Classes> listId(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Classes> list() {
		return (List<Classes>) repo.findAll();
	}

	@Override
	public List<Classes> recycleBin() {
		return repo.recycleBin();
	}

	@Override
	public void softDelete(int id) {
		repo.softDelete(id);
	}

	@Override
	public List<Students> listStudentByClass(int classId) {
		return repo.listStudentByClass(classId);
	}

}
