package com.everis.ejercicio1.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.service.IParentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Servicios de Parents")
@RequestMapping("/api/v1/parents")
/**
 * Mensaje de posibles errores en la Documentacion del swagger
 * 
 * @author kvilcave
 *
 */

@ApiOperation(value = "Is Alive operation", notes = "Return is the microservice is alive with a get operation returning the time")

@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),

		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),

		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),

		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),

		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })

public class RestParentsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IParentsService serv;

	/**
	 * Lista of the Familias.
	 * 
	 * @return list Parents.
	 */
	@ApiOperation(value = "Return list of parents")
	@GetMapping
	public ResponseEntity<List<Parents>> listar() {
		log.info("listado de parientes");
		return new ResponseEntity<List<Parents>>(serv.list(), HttpStatus.OK);

	}

	/**
	 * /** this function is responsible for making a record of a Parents.
	 * 
	 * @param per.
	 */
	@ApiOperation(value = "Create new parent")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody Parents per) {

		
		Parents perCreated = serv.create(per);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perCreated.getParentId()).toUri();
		
		log.info("Se creo con exito a " + per.getFirstName() + " " + per.getLastName());
		
		
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
	public String modificar(@Valid @RequestBody Parents per) {
		String mensaje = "";
		Optional<Parents> obj = serv.listId(per.getParentId());

		if (obj.isPresent()) {
			serv.update(per);
			mensaje = "Modificado con éxito!!";
			log.info(mensaje + " " + per.getParentId());
			new ResponseEntity<Parents>(HttpStatus.CREATED);

		} else {
			mensaje = "ID-" + per.getParentId()+" Pariente no existe";
			log.error(mensaje);
			throw new ModeloNotFoundException(mensaje);
		}

		return mensaje;
	}

	/**
	 * this function is responsible for deleting an existing record.
	 * 
	 * @param id - ingresar id existente.
	 */
	@ApiOperation(value = "Delete parent by id")
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Parents> par = serv.listId(id);
		if(par.isPresent()) {
			serv.delete(id);
		}else {
			
			throw new ModeloNotFoundException("ID-" + id);
		}
				

	}
	  /**
	   * Esta función es responsable de listar un registro.
	   * @param id - id dado por el usuario.
	   */
	  @ApiOperation(value = "Listar Parents por id")
	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Resource<Object> listarParentsPorId(@PathVariable("id") Integer id) {
		  
	   Optional<Parents> par = serv.listId(id);
		if(!par.isPresent()) {
			throw new ModeloNotFoundException("ID-" + id);
			
		}
		
		//return new ResponseEntity<Object>(par, HttpStatus.OK);
		
		  Resource<Object> resource = new Resource<Object>(par);
		  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarParentsPorId(id));

		  resource.add(linkto.withRel("links"));
		  
		  return resource;

	  }

}
