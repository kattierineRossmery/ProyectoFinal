package com.everis.ejercicio2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.ejercicio2.dto.ClassDTO;
import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.models.StudentsClasses;
import com.everis.ejercicio2.repository.IStudentsFeign;
import com.everis.ejercicio2.service.IClassService;
import com.everis.ejercicio2.service.IClassStudentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servicios de Classes")
@RequestMapping("/api/v2/classStudents")
public class RestClassStudentsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private IClassStudentsService serv;
	
		/**
		 * Modificando feign
		 */
	  
	  List<Integer> listStudentIds = new ArrayList<Integer>();
	  
	  List<Students> listStudents = new ArrayList<Students>();
	  
	  // llamo al feign service
	  @Autowired
	  IStudentsFeign iStudentsFeign;
	  
	
	  @Autowired
	  ClassDTO dtoClase;
	  @Autowired
	  private IClassService serviceClass;

	/**
	 * Lista of the Classes.
	 * 
	 * @return list Classes.
	 */
	@ApiOperation(value = "Retorna lista de Estudiantes vinculado a Clases")
	@GetMapping
	public ResponseEntity<List<StudentsClasses>> listar() {
	//	log.info("listado de Clases");
		return new ResponseEntity<List<StudentsClasses>>(serv.list(), HttpStatus.OK);

	}
	
	
	
	
	  /**
	   * Esta función es reposnsable de realizar un registro en
	   * familia.
	   * @param fam id de familia.
	   * @return objeto.
	   */
	  @ApiOperation(value = "Create new family")
	  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
	      consumes = MediaType.APPLICATION_JSON_VALUE)
	  public StudentsClasses insertar(@Valid @RequestBody StudentsClasses sc) {
		  
		 //StudentsClasses scCreated = serv.create();
			
			//URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{id2}/{id3}").buildAndExpand(scCreated.getClasses().getClassId(),scCreated.getStudents().getStudentId(),scCreated.getDateFrom()).toUri();
			
			//System.out.println("classtu" + scCreated);
			//log.info("Se creo con exito a " + fam.getFamilyName());
			
			
			//return ResponseEntity.created(location).build();
			return serv.create(sc);
	  }
	  
	  @GetMapping("/ids")
	  public List<Integer> getAllIdsByClassId(@RequestBody Integer classId) {

	    return serv.findByClassesClassId(classId);
	  }
	  
	  @GetMapping("/ids/{classId}")
	  public ClassDTO getClassAndAllStudentsByClassId(@PathVariable Integer classId) {
	    listStudentIds = (List<Integer>) getAllIdsByClassId(classId);

	    try {
	      listStudents = iStudentsFeign.listStudentsByStudentId(listStudentIds);

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	   Classes clase = new Classes();
	    clase = serviceClass.listId(classId);
	   

	    if (clase != null) {
	      log.info("clase no  es nulo");
	    } else {
	      log.info("clase es nulo");
	    }
	    dtoClase.setClassId(clase.getClassId());
	    dtoClase.setClassCode(clase.getClassCode());
	    dtoClase.setClassName(clase.getClassName());
	    dtoClase.setDeleted(clase.getDeleted());
	    dtoClase.setSubjects(clase.getSubjects());
	    dtoClase.setTeachers(clase.getTeachers());
	    dtoClase.setDateFrom(clase.getDateFrom());
	    dtoClase.setDateTo(clase.getDateTo());
	    dtoClase.setLstStudents(listStudents);
	    return dtoClase;
	  }

}
