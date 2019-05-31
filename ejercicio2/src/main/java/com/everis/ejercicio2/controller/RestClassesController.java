package com.everis.ejercicio2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.everis.ejercicio2.exception.ModeloNotFoundException;
import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.service.IClassService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Api(value = "Servicios de Classes")
@RequestMapping("/api/v2/classes")
public class RestClassesController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IClassService serv;

	/**
	 * Lista of the Classes.
	 * 
	 * @return list Classes.
	 */
	@ApiOperation(value = "Return list of Class")
	@GetMapping
	public ResponseEntity<List<Classes>> listar() {
		log.info("listado de Clases");
		return new ResponseEntity<List<Classes>>(serv.list(), HttpStatus.OK);

	}
	/**
	 * /Esta funcion es reponsable de crear un registro de Classes.
	 * 
	 * @param clas.
	 */
	@ApiOperation(value = "Crear nueva Clase")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody Classes clas) {

		
		Classes clasCreated = serv.create(clas);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clasCreated.getClassId()).toUri();
		
		log.info("Se creo con exito a " + clas.getClassName() );
		
		
		return ResponseEntity.created(location).build();
	
	}
	
	/**
	 * this function is responsible for updating an existing record.
	 * 
	 * @param per ingresar el registro a modificar.
	 * @return mensaje.
	 */
	@ApiOperation(value = "Update parent")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String modificar(@Valid @RequestBody Classes clas) {
		String mensaje = "";
		Optional<Classes> obj = serv.getAllId(clas.getClassId());

		if (obj.isPresent()) {
			serv.update(clas);
			mensaje = "Modificado con éxito!!";
			log.info(mensaje + " " + clas.getClassId());
			new ResponseEntity<Classes>(HttpStatus.CREATED);

		} else {
			mensaje = "ID-" + clas.getClassId()+" Class no existe";
			log.error(mensaje);
			throw new ModeloNotFoundException(mensaje);
		}

		return mensaje;
	}


	/**
	 * Esta función es responsable de eliminar un registro.
	 * 
	 * @param id - ingresar id existente.
	 */
	@ApiOperation(value = "Delete class by id")
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Classes> par = serv.getAllId(id);
		if(par.isPresent()) {
			serv.softDelete(id);
		}else {
			
			throw new ModeloNotFoundException("ID-" + id);
		}

	}

	 /**
	   * Esta función es responsable de listar un registro.
	   * @param id - id dado por el usuario.
	   */
	  @ApiOperation(value = "Listar Class por id")
	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Resource<Object> listarClassesPorId(@PathVariable("id") Integer id) {
		  Optional<Classes> clas = serv.getAllId(id);
			if(!clas.isPresent()) {
				throw new ModeloNotFoundException("ID -" + id);
				
			}
			
		//	return new ResponseEntity<Object>(stu, HttpStatus.OK);
			 Resource<Object> resource = new Resource<Object>(clas);
			  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarClassesPorId(id));

			  resource.add(linkto.withRel("links"));
			  
			  return resource;

	  }
	  @ApiOperation(value = "Listar Class por id")
	  @GetMapping(value = "/lista/{classId}", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<Students> listStudentByClass(@PathVariable("classId") Integer classId) {
			return serv.listStudentByClass(classId);
		}
}
