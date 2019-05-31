package com.everis.ejercicio1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.service.ParentsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestParentsController.class)
public class ParentsControllerTest {
	
	@Autowired
	  private MockMvc mockMvc;

	  @MockBean
	  private ParentsServiceImpl parentServiceImpl;

	  /**
	   * Instancia de Parents para realizar las pruebas unitarias.
	   */
	  Parents parentMock = new Parents(8, "M", "Robert", "Carlo", "Villavicencio", "otros", false); // getOne
	  List<Parents> listParentMock = new ArrayList<Parents>(); //list

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
	    listParentMock.add(new Parents(8, "M", "Robert", "Carlo", "Villavicencio", "otros", false));
	    listParentMock.add(new Parents(9, "F", "Raeliana", "Erika", "Reynoso", "otros",false));
	    
	    Mockito.when(parentServiceImpl.list()).thenReturn(listParentMock);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/parents/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{parentId:8,gender:M,firstName:Robert,middleName:Carlo,"
	        + "lastName:Villavicencio,otherParentDetails:otros,deleted:false},"
	        + "{parentId:9,gender:F,firstName:Raeliana,middleName:Erika,"
	        + "lastName:Reynoso,otherParentDetails:otros,deleted:false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	  }
	  
	  /**
	   * Valida que el resultado ES igual a lo esperado. Emplea assertEquals.
	   * 
	   * @throws Exception Control de excepciones.
	   */
	  @Test
	  public void testListarParentsPorIdTrue() throws Exception {
	    Mockito.when(parentServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(parentMock));

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/parents/8")
	        .accept(MediaType.APPLICATION_JSON);

	    
	    
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "{parentId:8,gender:M,firstName:Robert,middleName:Carlo," 
	    		+ "lastName:Villavicencio,otherParentDetails:otros,deleted:false}";
	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	  }
	  
	  /**
	   * Test que valida que el resultado NO es igual a lo esperado. 
	   * Emplea assertNotEquals.
	   * 
	   * @throws Exception Control de excepciones.
	   */
	  @Test
	  public void testListarParentsPorIdFalse() throws Exception {
		  Mockito.when(parentServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(parentMock));

		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/parents/8")
		        .accept(MediaType.APPLICATION_JSON);
		    
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		    
	    System.out.println(result.getResponse());
	    String expected = "{parentId:8,gender:M,firstName:Robert,middleName:Carlo," 
	    		+ "lastName:Villavicencio,otherParentDetails:otros}";

	    JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
	  }
	  
	  @Test
	  public void testCreate() throws Exception {
		  Parents parentMockss = new Parents(8, "M", "Robert", "Carlo", "Villavicencio", "otros", false); // getOne
	  ;
		  Mockito.when(parentServiceImpl.create(parentMockss)).thenReturn(parentMockss);
		
			//  parentServiceImpl.create(new Parents());
			  mockMvc
			        .perform(
			            post("/api/v1/parents").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Parents())))
			        .andExpect(status().isBadRequest());
			  
	  }
	  
	  
	  /**
	   * Test para validar controlar error en caso exista el id ingresado.
	   * El status a mostrar es 200.
	   * La eliminación es exitosa.
	   * @throws Exception manejo de excepxiones.
	   */
	 
	    @Test //(expected = ModeloNotFoundException.class)
	    public void testDelete() throws  Exception {
	    	 Mockito.when(parentServiceImpl.listId(Mockito.anyInt())).thenReturn(Optional.of(parentMock));
	 
	 	   final ResultActions result = mockMvc.perform(delete("/api/v1/parents/8"));
	 	    result.andExpect(status().isOk());
	    	    
	    }
	    
	  @Test
	  public void getPersonThatDoesNotExistReturnsError() throws Exception {
		  
		/* 
	    final ExceptionResponse exception = new ExceptionResponse(timestamp, mensaje, detalles);
	    given(parentServiceImpl.listId(ID)).willReturn(Optional.empty());
	    final ResultActions result = mockMvc.perform(get(BASE_PATH + "/" + ID));
	    result.andExpect(status().isNotFound());
	    result
	            .andExpect(jsonPath("$[0].timestamp", is(String.valueOf(ID))))
	            .andExpect(jsonPath("$[0].mensaje", is(exception.getMensaje())))
	            .andExpect(jsonPath("$[0].detalles", is(new ArrayList<String>())));
	  }*/
	  }
	  
	  public static String asJsonString(final Object obj) {
		    try {
		      return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }
		  }
	  
}
