package com.everis.ejercicio1.controller;

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.FamilyMembers;
import com.everis.ejercicio1.service.IFamilyMembersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "Servicio de Families")
@RequestMapping("/api/v1/familyMembers")
public class RestFamilyMembersController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFamilyMembersService serv;

	/**
	 * Lista de FamilyMembers.
	 * 
	 * @return lista de FamilyMembers.
	 */
	@ApiOperation(value = "Return list of family")
	@GetMapping
	public ResponseEntity<List<FamilyMembers>> listar() {

		return new ResponseEntity<List<FamilyMembers>>(serv.list(), HttpStatus.OK);

	}

	/**
	 * Esta funcion es reposnsable de realizar un registro en
	 * FamilyMembers.
	 * @param famMembers Se ingresa los datos para formar un objeto.
	 */
	
	@ApiOperation(value = "Create new family")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertar(@Valid @RequestBody FamilyMembers famMembers) {
		FamilyMembers famMem = serv.create(famMembers);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(famMem.getFamilyMemberId()).toUri();
		
		log.info("Se creo con exito a " + famMembers.getFamilyMemberId());
		
		
		return ResponseEntity.created(location).build();
	

	}
	
	/**
	 * Registrar FamilyMembers.
	 * @param familyMember ingresar id de familyMember.
	 * @param familyId ingresar id de family.
	 * @param parentOrStudentMember ingresar si es P(padre) 
	 * o S(estudiante)
	 * @param id ingresar id.
	 */
	@PostMapping("/api/1.0/familymembers/{familyId}/{parentOrStudentMember}/{id}")
	  public void add(@Valid @RequestBody FamilyMembers familyMember,
	      @PathVariable(value = "familyId") int familyId, 
	      @PathVariable(value = "parentOrStudentMember") String parentOrStudentMember,
	      @PathVariable(value = "id") int id) {
	    serv.post(familyMember, familyId, parentOrStudentMember, id);
	  }

	/**
     * Esta funcion es responsable de actualizar un registro. 
	 * @param famMembers ingresar registro a modificar.
	 * @return modified object.
	 */
	@ApiOperation(value = "Update family")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String modificar(@Valid @RequestBody FamilyMembers famMembers) {
		String mensaje = "";
		Optional<FamilyMembers> obj = serv.listId(famMembers.getFamilyMemberId());

		if (obj.isPresent()) {
			serv.update(famMembers);
			mensaje = "Modificado con éxito!!";
			log.info(mensaje);
			new ResponseEntity<FamilyMembers>(HttpStatus.CREATED);
		} else {
			mensaje = "ID - "+famMembers.getFamilyMemberId()+ " Pariente no existe";
			log.error(mensaje);
			throw new ModeloNotFoundException(mensaje);
		}

		return mensaje;
	}

	/**
	 * Esta funcion es responsable de eliminar un registro.
	 * 
	 * @param id - ingresar un id existente para eliminar.
	 */
	@ApiOperation(value = "Delete family members by id")
	@DeleteMapping("/{id}")
	public void eliminar(@Valid @PathVariable("id") Integer id) {

		Optional<FamilyMembers> famMem = serv.listId(id);
		if(famMem.isPresent()) {
			serv.delete(id);
		}else {
			
			throw new ModeloNotFoundException("ID-" + id);
		}
	}
	/**
	   * Esta función es responsable de listar un registro.
	   * @param id - id dado por el usuario.
	   */
	  @ApiOperation(value = "Listar family por id")
	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> listarParentsPorId(@PathVariable("id") Integer id) {
		  
		  Optional<FamilyMembers> fam = serv.listId(id);
			if(!fam.isPresent()) {
				throw new ModeloNotFoundException("ID-" + id);
				
			}
			
			return new ResponseEntity<Object>(fam, HttpStatus.OK);

	  
	  }

}
