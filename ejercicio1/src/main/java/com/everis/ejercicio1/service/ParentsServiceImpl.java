package com.everis.ejercicio1.service;

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.repository.IParentsDAO;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParentsServiceImpl implements IParentsService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
  @Autowired
  private IParentsDAO repo;

  @Override
  public Parents create(Parents parents) {
	  
	  log.info("Parent es: " + (parents != null));
	    log.info("getGender: " + parents.getGender());
	    log.info("getFirstName: " + parents.getFirstName());
	    log.info("getMiddleName: " + parents.getMiddleName());
	    log.info("getLastName: " + parents.getLastName());
	    log.info("getOtherParentDetails: " + parents.getOtherParentDetails());
    return repo.save(parents);
  }

  @Override
  public Parents update(Parents parents) {
    return repo.save(parents);
  }

  @Override
  public List<Parents> list() {
    return (List<Parents>) repo.findAll();
  }

@Override
public Optional<Parents> listId(int id) {
	return repo.findById(id);
}

@Override
public List<Parents> recycleBin() {
	return repo.recycleBin();
}

@Override
public void softDelete(int id) {
	repo.findById(id)
	        .map(
	            p -> {
	            	repo.softDelete(id);
	              return ResponseEntity.noContent().build();
	            })
	        .orElseThrow(() -> new ModeloNotFoundException("ID-"+ id +"no encontrado"));	
	
}

  

}
