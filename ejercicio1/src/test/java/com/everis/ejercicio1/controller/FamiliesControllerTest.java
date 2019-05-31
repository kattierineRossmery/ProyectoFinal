package com.everis.ejercicio1.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.service.FamiliesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(RestFamiliesController.class)
public class FamiliesControllerTest {

	 @Autowired
	  private MockMvc mokMvc;

	  @MockBean
	  FamiliesServiceImpl FAMService;
	 
	
	@Test
	public void testListarFalse() throws Exception {
		Families f = new Families();
	    
		f.setFamilyId(3);
		f.setFamilyName("Perez");
		
		Parents p = new Parents();
		p.setParentId(2);
		p.setGender("M");
	    p.setFirstName("Pedro");
	    p.setMiddleName("Carlos");
	    p.setLastName("Perez");
	    p.setOtherParentDetails("P");
	    p.setDeleted(false);
		f.setParentsss(p);
	   
	    f.setDeleted(false);
	    List<Families> listFamilies = new ArrayList<Families>();
	    listFamilies.add(f);
	    
	    Mockito.when(FAMService.list()).thenReturn(listFamilies);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/families/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mokMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"familyId\":3,\"familyName\":\"Perez\",\"parentsss\":{\"parentId\":2,\"gender\":\"M\",\"firstName\":\"Pedro\",\"middleName\":\"Carlos\",\"lastName\":\"Perez\",\"otherParentDetails\":\"P\",\"deleted\":false},\"deleted\":false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	  
	}


	/*
	 * @Test public void testInsertar() throws Exception { Families f = new
	 * Families();
	 * 
	 * f.setFamilyId(3); f.setFamilyName("Perez");
	 * 
	 * Parents p = new Parents(); p.setParentId(2); p.setGender("M");
	 * p.setFirstName("Pedro"); p.setMiddleName("Carlos"); p.setLastName("Perez");
	 * p.setOtherParentDetails("P"); p.setDeleted(false); f.setParentsss(p);
	 * 
	 * f.setDeleted(false); Mockito.when(FAMService.create(f)).thenReturn(null);
	 * 
	 * 
	 * FAMService.create(new Families()); mokMvc .perform(
	 * post("/api/v1/students").contentType(MediaType.APPLICATION_JSON).content(
	 * asJsonString(new Students()))) .andExpect(status().isBadRequest()); }
	 */

	/**
	   * Test para validar controlar error en caso no exista el id ingresado.
	   * El status a mostrar es 404.
	   * 
	   * @throws Exception manejo de excepxiones.
	   */
	@Test
	public void testEliminar() throws Exception {
	    this.mokMvc
	    .perform(MockMvcRequestBuilders.delete("/api/v1/families/100", 100))
	    .andExpect(status().isNotFound());
	}

	@Test
	public void testListarFamiliesPorId() throws Exception {
		
Families f = new Families();
	    
		f.setFamilyId(3);
		f.setFamilyName("Perez");
		
		Parents p = new Parents();
		p.setParentId(2);
		p.setGender("M");
	    p.setFirstName("Pedro");
	    p.setMiddleName("Carlos");
	    p.setLastName("Perez");
	    p.setOtherParentDetails("P");
	    p.setDeleted(false);
		f.setParentsss(p);
	   
	    f.setDeleted(false);
		
		//Families famMocks = new Families(3, "Villavicencio",4, false); // getOne
		
		  
		 Mockito.when(FAMService.listId(Mockito.anyInt())).thenReturn(Optional.of(f));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/families/3")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mokMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"familyId\":3,\"familyName\":\"Perez\",\"parentsss\":{\"parentId\":2,\"gender\":\"M\",\"firstName\":\"Pedro\",\"middleName\":\"Carlos\",\"lastName\":\"Perez\",\"otherParentDetails\":\"P\",\"deleted\":false},\"deleted\":false}";
		    		
		    		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		  
		
	}

	@Test
	public void testListarFamiliesPorIdFalse() throws Exception {
		
Families f = new Families();
	    
		f.setFamilyId(3);
		f.setFamilyName("Perez");
		
		Parents p = new Parents();
		p.setParentId(2);
		p.setGender("M");
	    p.setFirstName("Pedro");
	    p.setMiddleName("Carlos");
	    p.setLastName("Perez");
	    p.setOtherParentDetails("P");
	    p.setDeleted(false);
		f.setParentsss(p);
	   
	    f.setDeleted(false);
		
		//Families famMocks = new Families(3, "Villavicencio",4, false); // getOne
		
		  
		 Mockito.when(FAMService.listId(Mockito.anyInt())).thenReturn(Optional.of(f));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/families/3")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mokMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"familyId\":3,\"familyName\":\"Perez\",\"parentsss\":{\"parentId\":2,\"gender\":\"M\",\"lastName\":\"Perez\",\"otherParentDetails\":\"P\",\"deleted\":false},\"deleted\":false}";
		    		
		    		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		  
		
	}
	
	  public static String asJsonString(final Object obj) {
		    try {
		      return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }
		  }

}
