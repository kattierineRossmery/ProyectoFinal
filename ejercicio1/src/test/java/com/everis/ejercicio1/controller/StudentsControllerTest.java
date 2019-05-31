package com.everis.ejercicio1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.everis.ejercicio1.models.Students;
import com.everis.ejercicio1.service.StudentsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(RestStudentsController.class)
public class StudentsControllerTest {

	  @Autowired
	  private MockMvc mokMvc;

	  @MockBean
	  StudentsServiceImpl studentService;

	  
	
	  List<Students> listStudentMock = new ArrayList<Students>(); //list

	  //Students studentMock = new Students();
	  
	
	@Test
	public void testListar() throws Exception {
		listStudentMock.add(new Students(3, "M", "Robert", "Carlo", "Villavicencio", "otros", false));
		listStudentMock.add(new Students(4, "F", "Raeliana", "Koral", "Reynoso", "otros",false));
	    
	    Mockito.when(studentService.list()).thenReturn(listStudentMock);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mokMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"studentId\":3,\"gender\":\"M\",\"firstName\":\"Robert\",\"middleName\":\"Carlo\",\"lastName\":\"Villavicencio\",\"dateOfBirth\":null,\"otherStudentDetails\":\"otros\",\"deleted\":false},{\"studentId\":4,\"gender\":\"F\",\"firstName\":\"Raeliana\",\"middleName\":\"Koral\",\"lastName\":\"Reynoso\",\"dateOfBirth\":null,\"otherStudentDetails\":\"otros\",\"deleted\":false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	  }

	@Test
	public void testInsertar() throws Exception {
		  
		Students studentMock = new Students(3, "M", "Robert", "Carlo", "Villavicencio", null,"otros", false); // getOne
		
		  Mockito.when(studentService.create(studentMock)).thenReturn(null);
		
		
			 studentService.create(new Students());
			 		mokMvc
				        .perform(
				            post("/api/v1/students").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Students())))
				        .andExpect(status().isBadRequest());
				  
		  
	}



	/**
	   * Test para validar controlar error en caso no exista el id ingresado.
	   * El status a mostrar es 404.
	   * 
	   * @throws Exception manejo de excepxiones.
	   */
	@Test //(expected = ModeloNotFoundException.class)
	public void testEliminar() throws Exception {
	    this.mokMvc
	    .perform(MockMvcRequestBuilders.delete("/api/v1/students/100", 100))
	    .andExpect(status().isNotFound());
	    
	 	

	}


	@Test
	public void testListById() throws Exception {
		  
		Students studentMocksid = new Students(3, "M", "Robert", "Carlo", "Villavicencio","otros", false); // getOne
		
		  
		 Mockito.when(studentService.listId(Mockito.anyInt())).thenReturn(Optional.of(studentMocksid));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students/3")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mokMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"studentId\":3,\"gender\":\"M\",\"firstName\":\"Robert\",\"middleName\":\"Carlo\",\"lastName\":\"Villavicencio\",\"dateOfBirth\":null,\"otherStudentDetails\":\"otros\",\"deleted\":false}";
		    		
		    		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		  
		}
	
	@Test
	public void testListByIdFalse() throws Exception {
		  
		Students studentMocksid = new Students(3, "M", "Robert", "Carlo", "Villavicencio","otros", false); // getOne
		
		  
		 Mockito.when(studentService.listId(Mockito.anyInt())).thenReturn(Optional.of(studentMocksid));
		    
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students/3")
		      .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mokMvc.perform(requestBuilder).andReturn();
		    
		    System.out.println(result.getResponse());
		    String expected = "{\"studentId\":3,\"gender\":\"M\",\"firstName\":\"Robert\",\"middleName\":\"Carlo\",\"lastName\":\"Villavicencio\",\"otherStudentDetails\":\"otros\",\"deleted\":false}";
		    		
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
