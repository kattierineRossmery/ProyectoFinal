package com.everis.ejercicio2.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.service.ISubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servicios de Subjects")
@RequestMapping("/api/v1/subjects")
public class RestSubjectsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISubjectService serv;

	/**
	 * Lista of the Subjects.
	 * 
	 * @return list Subjects.
	 */
	@ApiOperation(value = "Return list of Subjects")
	@GetMapping
	public ResponseEntity<List<Subjects>> listar() {
		log.info("listado de Clases");
		return new ResponseEntity<List<Subjects>>(serv.list(), HttpStatus.OK);

	}
	/**
	 * /Esta funcion es reponsable de crear un registro de Subjects.
	 * 
	 * @param clas.
	 */
	@ApiOperation(value = "Crear nueva Asignatura")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody Subjects sub) {

		
		Subjects subCreated = serv.create(sub);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subCreated.getSubjectId()).toUri();
		
		log.info("Se creo con exito a " + sub.getSubjectName() );
		
		
		return ResponseEntity.created(location).build();
	
	}
	
	/**
	 * ESta función es responsable de actualizar un registro.
	 * 
	 * @param clas ingresar el registro a modificar.
	 * @return mensaje.
	 */
	@ApiOperation(value = "Update parent")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String modificar(@Valid @RequestBody Subjects sub) {
		String mensaje = "";
		Optional<Subjects> obj = serv.listId(sub.getSubjectId());

		if (obj.isPresent()) {
			serv.update(sub);
			mensaje = "Modificado con éxito!!";
			log.info(mensaje + " " + sub.getSubjectId());
			new ResponseEntity<Subjects>(HttpStatus.CREATED);

		} else {
			mensaje = "ID-" + sub.getSubjectId()+" Subject no existe";
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
		Optional<Subjects> sub = serv.listId(id);
		if(sub.isPresent()) {
			serv.delete(id);
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
	  public Resource<Object> listarSubjectsPorId(@PathVariable("id") Integer id) {
		  Optional<Subjects> sub = serv.listId(id);
			if(!sub.isPresent()) {
				throw new ModeloNotFoundException("ID-" + id);
				
			}
			
			 Resource<Object> resource = new Resource<Object>(sub);
			  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarSubjectsPorId(id));

			  resource.add(linkto.withRel("links"));
			  
			  return resource;

	  }
}
