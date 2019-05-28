package com.everis.ejercicio1.controller;

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Students;
import com.everis.ejercicio1.service.IStudentsService;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Api(value = "Servicios de Student")
@RequestMapping("/api/v1/students")
public class RestStudentsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IStudentsService serv;

	/**
	 * List of the Students.
	 * 
	 * @return list Students.
	 */
	@ApiOperation(value = "Return list of family")
	@GetMapping
	public ResponseEntity<List<Students>> listar() {

		log.info("lista ok");
		return new ResponseEntity<List<Students>>(serv.list(), HttpStatus.OK);

	}

	/**
	 * this function is responsible for making a record of a family.
	 * 
	 * @param stu - stu almacena el registro.
	 */
	@ApiOperation(value = "Create new students")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody Students stu) {
		
		Students stuCreated = serv.create(stu);;
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stuCreated.getStudentId()).toUri();
		
		log.info("Se registro a"+ stu.getFirstName()+" "+ stu.getLastName());
		
		
		return ResponseEntity.created(location).build();
	}

	/**
	 * this function is responsible for updating an existing record.
	 * 
	 * @param stu stu.
	 * @return obj
	 */
	@ApiOperation(value = "Update students")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String modificar(@Valid @RequestBody Students stu) {
		String mensaje = "";
		Optional<Students> obj = serv.listId(stu.getStudentId());

		if (obj.isPresent()) {
			serv.update(stu);
			mensaje = "Modificado con éxito!! ";
			new ResponseEntity<Students>(HttpStatus.CREATED);

			log.info(mensaje + stu.getStudentId());
		} else {
			mensaje = "ID - "+stu.getStudentId()+ " de Students no existe";
			log.error(mensaje);
			throw new ModeloNotFoundException(mensaje);

		}

		return mensaje;
	}

	/**
	 * this function is responsible for deleting an existing record.
	 * 
	 * @param id - id de Estudiante existente.
	 */
	@ApiOperation(value = "Delete students by id")
	@DeleteMapping("/{id}")
	public void eliminar(@Valid @PathVariable("id") Integer id) {
		 Optional<Students> stu = serv.listId(id);
			if(stu.isPresent()) {
				serv.delete(id);
			}else {
				
				throw new ModeloNotFoundException("ID-" + id);
			}
	   

	}
	 /**
	   * Esta función es responsable de listar un registro.
	   * @param id - id dado por el usuario.
	   */
	  @ApiOperation(value = "Listar Students por id")
	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Resource<Object> listarStudentsPorId(@PathVariable("id") Integer id) {
		  Optional<Students> stu = serv.listId(id);
			if(!stu.isPresent()) {
				throw new ModeloNotFoundException("ID-" + id);
				
			}
			
		//	return new ResponseEntity<Object>(stu, HttpStatus.OK);
			 Resource<Object> resource = new Resource<Object>(stu);
			  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarStudentsPorId(id));

			  resource.add(linkto.withRel("links"));
			  
			  return resource;

	  }
}
