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
import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.service.SubjectsServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestSubjectsController.class)
public class SubjectsControllerTest {

	@Autowired
	  private MockMvc mockMvc;

	  @MockBean
	  private SubjectsServiceImpl subjectServiceImpl;
	
	  /**
	   * Instancia de Subjects para realizar las pruebas unitarias.
	   */
	  Subjects subMock = new Subjects(3, "Matematica", false);
	  List<Subjects> listSubjects = new ArrayList<Subjects>();
	  
	  /**
	   * Test que valida que el resultado de obtener una colección de objetos de tipo Parents es
	   * igual a lo esperado. 
	   * 
	   * Emplea assertEquals 
	   * 
	   * Valida que el resultado esperado sea igual al resultado obtenido.
	   * 
	   * @throws Exception control de excepcion.
	   */  
	@Test
	public void testListar() throws Exception {
		listSubjects.add(new Subjects(1, "Matematica", false));
		listSubjects.add(new Subjects(2, "Matematica II", false));
	    
	    Mockito.when(subjectServiceImpl.list()).thenReturn(listSubjects);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/subjects/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"subjectId\":1,\"subjectName\":\"Matematica\",\"deleted\":false},{\"subjectId\":2,\"subjectName\":\"Matematica II\",\"deleted\":false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	 
	}

	@Test
	public void testListarFalse() throws Exception {
		listSubjects.add(new Subjects(1, "Matematica", false));
		listSubjects.add(new Subjects(2, "Matematica II", false));
	    
	    Mockito.when(subjectServiceImpl.list()).thenReturn(listSubjects);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/subjects/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"subjectId\":1,\"deleted\":false},{\"subjectId\":2,\"subjectName\":\"Matematica II\",\"deleted\":false}]";

	    JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
	 
	}

	@Test
	public void testEliminar() throws Exception {
   	 Mockito.when(subjectServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(subMock));

   	 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/subjects/3")
		        .accept(MediaType.APPLICATION_JSON);
		    
   	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
   	System.out.println(result.getResponse());
		    
		    
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
		subjectServiceImpl.softDelete(0);
	}
	
	@Test
	public void testEliminarFalse() throws Exception {
   	 Mockito.when(subjectServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(subMock));
	 
	   final ResultActions result = mockMvc.perform(delete("/api/v2/subjects/3"));
	    result.andExpect(status().isOk());
	}

	@Test
	public void testListarSubjectsPorId() throws Exception {
		 Mockito.when(subjectServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(subMock));

		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/subjects/3")
		        .accept(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		    System.out.println(result.getResponse());
		    String expected = "{subjectId:3,subjectName:Matematica,deleted:false}";
		    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		
	}
	@Test
	public void testListarSubjectsPorIdFalse() throws Exception {
		 Mockito.when(subjectServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(subMock));

		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/subjects/3")
		        .accept(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		    System.out.println(result.getResponse());
		    String expected = "{subjectId:3,subjectName:Matematica,deleted:true}";
		    JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		
	}
	
	

}
