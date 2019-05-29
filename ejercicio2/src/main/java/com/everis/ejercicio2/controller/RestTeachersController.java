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
import com.everis.ejercicio2.models.Teachers;
import com.everis.ejercicio2.service.ITeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servicios de Teachers")
@RequestMapping("/api/v2/teachers")
public class RestTeachersController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITeacherService serv;
	
	//@Autowired
	//private ITeacherDAO dao;

	/**
	 * Lista of the Teachers.
	 * 
	 * @return list Teachers.
	 */
	@ApiOperation(value = "Return list of teachers")
	@GetMapping
	public ResponseEntity<List<Teachers>> listar() {
		log.info("listado de Clases");
		return new ResponseEntity<List<Teachers>>(serv.list(), HttpStatus.OK);

	}
	/**
	 * /Esta funcion es reponsable de crear un registro de Teachers.
	 * 
	 * @param clas.
	 */
	@ApiOperation(value = "Crear nuevo Profesor")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody Teachers prof) {

		
		Teachers profCreated = serv.create(prof);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profCreated.getTeachersId()).toUri();
		
		log.info("Se creo con exito a " + prof.getFirstName() +" "+ prof.getLastName());
		
		
		return ResponseEntity.created(location).build();
	
	}
	
	/**
	 * Esta función es respinsable de ingresar un resgistro.
	 * 
	 * @param per ingresar el registro a modificar.
	 * @return mensaje.
	 */
	@ApiOperation(value = "Update Teachers")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String modificar(@Valid @RequestBody Teachers prof) {
		String mensaje = "";
		Optional<Teachers> obj = serv.listId(prof.getTeachersId());

		if (obj.isPresent()) {
			serv.update(prof);
			mensaje = "Modificado con éxito!!";
			log.info(mensaje + " " + prof.getTeachersId());
			new ResponseEntity<Teachers>(HttpStatus.CREATED);

		} else {
			mensaje = "ID-" + prof.getTeachersId()+" Teachers no existe";
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
		Optional<Teachers> tea = serv.listId(id);
		if(tea.isPresent()) {
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
		  Optional<Teachers> prof = serv.listId(id);
			if(!prof.isPresent()) {
				throw new ModeloNotFoundException("ID-" + id);
				
			}
			
			 Resource<Object> resource = new Resource<Object>(prof);
			  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarClassesPorId(id));

			  resource.add(linkto.withRel("links"));
			  
			  return resource;

	  }
	
	
	  
}
