package com.everis.ejercicio1.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.repository.IParentsDAO;

@RunWith(MockitoJUnitRunner.class)
public class ParentsServiceImplTest {

	@InjectMocks
	  private ParentsServiceImpl parentService;
	  
	  @Mock
	  private IParentsDAO parentRepo;
	
	  
	  
	@Test
	public void testCreate() {
		Parents p = new Parents();
	    p.setParentId(2);
	    p.setGender("M");
	    p.setFirstName("Pedro");
	    p.setMiddleName("Carlos");
	    p.setLastName("Perez");
	    
	    p.setOtherParentDetails("P");
	    p.setDeleted(false);
	    
	    List<Parents> listaParents = new ArrayList<>();
	    listaParents.add(p);
	    
	    //lista
	//    when(parentRepo.findAll()).thenReturn(listaParents);
	    //listar por ID
	  //  when(parentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(p));
	    
	}

	@Test
	public void testUpdate() {
//		given(parentRepo.save(new Parents())).willReturn(new Parents());
//	    assertEquals(new Parents(), 
		
	    		parentService.update(new Parents());
	}

	@Test
	public void testList() {
		List<Parents> list = new ArrayList<>();
	    given(parentRepo.findAll()).willReturn(list);
	    assertEquals(list, parentService.list());
	}

	/**
	 * Este teste admite validar que en caso el id no existe manda una excepcion.
	 * Admite como parametro la excepcion
	 * 
	 * El test fallara si el metodo no lanza una excepcion de ModeloNotFoundException.
	 * @throws ModeloNotFoundException validacion de excepcion
	 */
	@Test(expected = ModeloNotFoundException.class)
	public void testSoftDelete() throws ModeloNotFoundException{
		parentService.softDelete(0);
	}

}
