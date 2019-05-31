package com.everis.ejercicio1.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import com.everis.ejercicio1.dto.FamiliesDTO;
import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.FamilyMembers;
import com.everis.ejercicio1.service.IFamiliesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Api(value = "Servicios de Familia")
@RequestMapping("/api/v1/families")
public class RestFamiliesController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	
  @Autowired
  private IFamiliesService serv;

  /**
   * GET - Lista de Familias.
   * @return lista Families.
   */
  @ApiOperation(value = "Return list of family")
  @GetMapping
  public ResponseEntity<List<Families>> listar() {
	  log.info("lista de familias");
    return new ResponseEntity<List<Families>>(serv.list(), HttpStatus.OK);
    

  }
  @GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FamiliesDTO> listarHateoas(){
	  List<Families> fam=new ArrayList<>();
	  List<FamiliesDTO> famDTO= new ArrayList<>();
	  
	  fam = serv.list();
	  
	  for(Families f : fam) {
		  FamiliesDTO fd = new FamiliesDTO();
		  fd.setFamilyId(f.getFamilyId());
		  fd.setParentsss(f.getParentsss());
		  
		  ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarFamiliesPorId(f.getFamilyId()));
		  fd.add(linkTo.withRel("links"));
		  famDTO.add(fd);
		  
		  ControllerLinkBuilder linkTo1 = linkTo(methodOn(RestParentsController.class).listarParentsPorId(f.getParentsss().getParentId()));
		  fd.add(linkTo1.withRel("Links"));
		  famDTO.add(fd);
	  }
	  
	  return famDTO;
  }

  /**
   * Lista de miembros de la familia por Id.
   * @param family_id id de familia.
   * @return a lista de miembros de la familia.
   */
  @ApiOperation(value = "Return list of family by id members")
  @GetMapping(value = "/{family_id}/members")
  public ResponseEntity<List<FamilyMembers>> listarMembersId(@PathVariable("family_id") Integer family_id) {
	  log.info("lista de miembros de una familias");
    return new ResponseEntity<List<FamilyMembers>>(serv.findByFamiliesFamily_id(family_id), HttpStatus.OK);

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
  public ResponseEntity<Object> insertar(@Valid @RequestBody Families fam) {
	  
	  Families famCreated = serv.create(fam);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(famCreated.getFamilyId()).toUri();
		
		log.info("Se creo con exito a " + fam.getFamilyName());
		
		
		return ResponseEntity.created(location).build();
  }

  /**
   * Esta función es responsable de actualizar un registro.
   * @param fam the Families.
   * @return objeto modificado.
   */
  @ApiOperation(value = "Update family")
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String modificar(@Valid @RequestBody Families fam) {

	  String mensaje = "";
		Optional<Families> obj = serv.listId(fam.getFamilyId());

		if (obj.isPresent()) {
			 serv.update(fam);
			mensaje = "Modificado con éxito!! a la familia " + fam.getFamilyName();
			log.info(mensaje);
			new ResponseEntity<Families>(HttpStatus.CREATED);

		} else {
			mensaje = "Id -"+fam.getFamilyId()+" Families no existe";
			log.error(mensaje);
			throw new ModeloNotFoundException(mensaje);
		}

		return mensaje;
  }

  /**
   * Esta función es responsable de eliminar un registro.
   * @param id - id dado por el usuario, este tiene que existir.
   */
  @ApiOperation(value = "Listar family por id")
  @DeleteMapping("/{id}")
  public void eliminar(@Valid @PathVariable("id") Integer id) {
	  
	  Optional<Families> fam = serv.listId(id);
		if(fam.isPresent()) {
			serv.softDelete(id);
		}else {
			
			throw new ModeloNotFoundException("ID-" + id);
		}
  }
  
  /**
   * Esta función es responsable de listar un registro.
   * @param id - id dado por el usuario.
   * @return objeto Families.
   */
  @ApiOperation(value = "Listar family por id")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resource<Object> listarFamiliesPorId(@PathVariable("id") Integer id) {
	  
	  Optional<Families> fam = serv.listId(id);
		if(!fam.isPresent()) {
			throw new ModeloNotFoundException("ID -" + id);
			
		}
		
		
		  Resource<Object> resource = new Resource<Object>(fam);
		  
		  return resource;
	  }

  /**
   * HATEOAS
   * Esta función es responsable de listar un registro.
   * @param id - id dado por el usuario.
   * @return objeto Families.
   */
  @ApiOperation(value = "Listar family por id")
  @GetMapping(value = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resource<Object> listarFamiliesPorIdHa(@PathVariable("id") Integer id) {
	  
	  Optional<Families> fam = serv.listId(id);
		if(!fam.isPresent()) {
			throw new ModeloNotFoundException("ID-" + id);
			
		}
		
		//return new ResponseEntity<Object>(fam, HttpStatus.OK);
		
		  Resource<Object> resource = new Resource<Object>(fam);
		  ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).listarFamiliesPorId(id));

		  resource.add(linkto.withRel("links"));
		  
		  return resource;
	  }

   
}
