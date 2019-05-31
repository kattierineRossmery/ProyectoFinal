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
import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.repository.IFamiliesDAO;

@RunWith(MockitoJUnitRunner.class) 
public class FamiliesServiceImplTest2 {

	@InjectMocks
	  private FamiliesServiceImpl familiesService;
	  
	  @Mock
	  private IFamiliesDAO famRepo;
	
	@Test
	public void testCreate() {
		Families f = new Families();
	    
		f.setFamilyId(4);
		f.setFamilyName("Perez");
		
		Parents p = new Parents();
		p.setParentId(2);
		f.setParentsss(p);
	   
	    f.setDeleted(false);
	    List<Families> listaFamilies = new ArrayList<>();
	    listaFamilies.add(f);
	    
	    //lista
	  // when(famRepo.findAll()).thenReturn(listaFamilies);
	    //listar por ID
	   // when(famRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(f));
	    
	}


	@Test
	public void testList() {
		List<Families> list = new ArrayList<>();
	    given(famRepo.findAll()).willReturn(list);
	    assertEquals(list, familiesService.list());
	}



	@Test
	public void testSoftDelete() throws ModeloNotFoundException{
		familiesService.softDelete(0);
	}

}
