package com.everis.ejercicio2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.everis.ejercicio2.exception.ModeloNotFoundException;
import com.everis.ejercicio2.models.Schools;
import com.everis.ejercicio2.models.Teachers;
import com.everis.ejercicio2.service.TeachersServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestTeachersController.class)
public class TeachersControllerTest {

	@Autowired
	  private MockMvc mockMvc;

	  @MockBean
	  private TeachersServiceImpl teaServiceImpl;
	
	  
	/*
	 * Subjects subMock = new Subjects(3, "Matematica", false); List<Subjects>
	 * listSubjects = new ArrayList<Subjects>();
	 * 
	 * Teachers steaMock = new Teachers(1,"F","Ana","Rosa","Ramos",
	 * "otros",subMock.ge, false); List<Teachers> listTeachers = new
	 * ArrayList<Subjects>();
	 */
	  
	@Test
	public void testListar() throws Exception {
Teachers t = new Teachers();
	    t.setTeachersId(1);
	    t.setGender("F");
	    t.setFirstName("Ana");
	    t.setLastName("Ramos");
	    t.setOtherTeacherDetails("otros");
	    Schools s = new Schools();
	    s.setSchoolId(1);
	    s.setSchoolName("U.E Jose Maria");
	    
	    t.setDeleted(false);
	
	    List<Teachers> listTeacher = new ArrayList<Teachers>();
	    listTeacher.add(t);
	    
	    Mockito.when(teaServiceImpl.list()).thenReturn(listTeacher);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/teachers/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	  
	}
	
	@Test
	public void testListarFalse() throws Exception {
		Teachers t = new Teachers();
	    t.setTeachersId(1);
	    t.setGender("F");
	    t.setFirstName("Ana");
	    t.setLastName("Ramos");
	    t.setOtherTeacherDetails("otros");
	    Schools s = new Schools();
	    s.setSchoolId(1);
	    s.setSchoolName("U.E Jose Maria");
	    
	    t.setDeleted(false);
	
	    List<Teachers> listTeacher = new ArrayList<Teachers>();
	    listTeacher.add(t);
	    
	    Mockito.when(teaServiceImpl.list()).thenReturn(listTeacher);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/teachers/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"teachersId\":8,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false}]";

	    JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
	  
	}

	@Test
	public void testEliminar() throws Exception {
		Teachers t = new Teachers();
	    t.setTeachersId(1);
	    t.setGender("F");
	    t.setFirstName("Ana");
	    t.setLastName("Ramos");
	    t.setOtherTeacherDetails("otros");
	    Schools s = new Schools();
	    s.setSchoolId(1);
	    s.setSchoolName("U.E Jose Maria");
	    
	    t.setDeleted(false);
		 Mockito.when(teaServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(t));
		    
		
   	 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/teachers/1")
		        .accept(MediaType.APPLICATION_JSON);
		    
   	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
   	System.out.println(result.getResponse());
		    
		    
	}
	
	@Test
	public void testEliminarFalse() throws Exception {
		Teachers tea = new Teachers();
   	 Mockito.when(teaServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(tea));
	 
	   final ResultActions result = mockMvc.perform(delete("/api/v2/teachers/ll"));
	    result.andExpect(status().isBadRequest());

			    
	}
	
	/**
	 * Este teste admite validar que en caso el id no existe manda una excepcion.
	 * Admite como parametro la excepcion
	 * 
	 * El test fallara si el metodo no lanza una excepcion de ModeloNotFoundException.
	 * @throws ModeloNotFoundException validacion de excepcion
	 */
	@Test //(expected = ModeloNotFoundException.class)
	public void testSoftDeleteException() throws ModeloNotFoundException{
		teaServiceImpl.softDelete(0);
	}
	
	@Test
	public void testListarClassesPorId() throws Exception {
		Teachers t = new Teachers();
	    t.setTeachersId(1);
	    t.setGender("F");
	    t.setFirstName("Ana");
	    t.setLastName("Ramos");
	    t.setOtherTeacherDetails("otros");
	    Schools s = new Schools();
	    s.setSchoolId(1);
	    s.setSchoolName("U.E Jose Maria");
	    
	    t.setDeleted(false);
		
		
		  
		 Mockito.when(teaServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(t));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/teachers/1")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false,\"_links\":{\"links\":{\"href\":\"http://localhost/api/v2/teachers/1\"}}}";
		    		
		    		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		  
	}
	
	@Test
	public void testListarClassesPorIdFalse() throws Exception {
		Teachers t = new Teachers();
	    t.setTeachersId(1);
	    t.setGender("F");
	    t.setFirstName("Ana");
	    t.setLastName("Ramos");
	    t.setOtherTeacherDetails("otros");
	    Schools s = new Schools();
	    s.setSchoolId(1);
	    s.setSchoolName("U.E Jose Maria");
	    
	    t.setDeleted(false);
		
		
		  
		 Mockito.when(teaServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(t));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/teachers/1")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"teachersId\":9,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false,\"_links\":{\"links\":{\"href\":\"http://localhost/api/v2/teachers/1\"}}}";
		    		
		    		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		  
	}

}
