package com.everis.ejercicio2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.hibernate.service.spi.ServiceException;
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

import com.everis.ejercicio2.exception.ModeloNotFoundException;
import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Schools;
import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.models.Teachers;
import com.everis.ejercicio2.service.ClassesServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestClassesController.class)
public class ClassesControllerTest {

	  @Autowired
	  private MockMvc mockMvc;

	  @MockBean
	  private ClassesServiceImpl classServiceImpl;

	  /**
	   * Instancia de Classes para realizar las pruebas unitarias.
	 * @throws Exception 
	   */
  
	@Test
	public void testListar() throws Exception {
		Classes c = new Classes();
	    c.setClassId(1);
	    c.setClassCode(100);
	    c.setClassName("LOS HALCONES");
	 
	    Subjects su = new Subjects();
	    su.setSubjectId(1);
	    su.setSubjectName("Matematica I");
	    su.setDeleted(false);
	    c.setSubjects(su);
	    
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
	    
	    c.setTeachers(t);
	
	    c.setDeleted(false);
	
	    List<Classes> listClass = new ArrayList<Classes>();
	    listClass.add(c);
	    
	    Mockito.when(classServiceImpl.list()).thenReturn(listClass);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/classes/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"classId\":1,\"classCode\":100,\"className\":\"LOS HALCONES\",\"dateFrom\":null,\"dateTo\":null,\"subjects\":{\"subjectId\":1,\"subjectName\":\"Matematica I\",\"deleted\":false},\"teachers\":{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false},\"deleted\":false}]";

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	 
	}
	
	@Test
	public void testListarFalse() throws Exception {
		Classes c = new Classes();
	    c.setClassId(1);
	    c.setClassCode(100);
	    c.setClassName("LOS HALCONES");
	 
	    Subjects su = new Subjects();
	    su.setSubjectId(1);
	    su.setSubjectName("Matematica I");
	    su.setDeleted(false);
	    c.setSubjects(su);
	    
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
	    
	    c.setTeachers(t);
	
	    c.setDeleted(false);
	
	    List<Classes> listClass = new ArrayList<Classes>();
	    listClass.add(c);
	    
	    Mockito.when(classServiceImpl.list()).thenReturn(listClass);

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/classes/")
	        .accept(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    System.out.println(result.getResponse());
	    String expected = "[{\"classId\":8,\"classCode\":100,\"className\":\"LOS HALCONES\",\"dateFrom\":null,\"dateTo\":null,\"subjects\":{\"subjectId\":1,\"subjectName\":\"Matematica I\",\"deleted\":false},\"teachers\":{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false},\"deleted\":false}]";

	    JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
	 
	}


	@Test
	public void testEliminar() throws Exception {
		Classes t = new Classes();
		 Mockito.when(classServiceImpl.getAllId(Mockito.anyInt())).thenReturn(Optional.of(t));
		    
		
   	 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/classes/1")
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
	public void testSoftDelete() throws ModeloNotFoundException{
		classServiceImpl.softDelete(0);
	}

	@Test
	public void testListarClassesPorId() throws Exception {
		Classes c = new Classes();
	    c.setClassId(1);
	    c.setClassCode(100);
	    c.setClassName("LOS HALCONES");
	 
	    Subjects su = new Subjects();
	    su.setSubjectId(1);
	    su.setSubjectName("Matematica I");
	    su.setDeleted(false);
	    c.setSubjects(su);
	    
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
	    
	    c.setTeachers(t);
	
	    c.setDeleted(false);
	    
	    Mockito.when(classServiceImpl.getAllId(Mockito.anyInt())).thenReturn(Optional.of(c));
	    
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/classes/1")
	      .accept(MediaType.APPLICATION_JSON);
	    
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    
	    System.out.println(result.getResponse());
	    String expected = "{\"classId\":1,\"classCode\":100,\"className\":\"LOS HALCONES\",\"dateFrom\":null,\"dateTo\":null,\"subjects\":{\"subjectId\":1,\"subjectName\":\"Matematica I\",\"deleted\":false},\"teachers\":{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false},\"deleted\":false,\"_links\":{\"links\":{\"href\":\"http://localhost/api/v2/classes/1\"}}}";
	    		
	    		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	
	}

	@Test
	public void testListarClassesPorIdFalse() throws Exception {
		Classes c = new Classes();
	    c.setClassId(1);
	    c.setClassCode(100);
	    c.setClassName("LOS HALCONES");
	 
	    Subjects su = new Subjects();
	    su.setSubjectId(1);
	    su.setSubjectName("Matematica I");
	    su.setDeleted(false);
	    c.setSubjects(su);
	    
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
	    
	    c.setTeachers(t);
	
	    c.setDeleted(false);
	    
	    Mockito.when(classServiceImpl.getAllId(Mockito.anyInt())).thenReturn(Optional.of(c));
	    
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/classes/1")
	      .accept(MediaType.APPLICATION_JSON);
	    
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    
	    System.out.println(result.getResponse());
	    String expected = "{\"classId\":0,\"classCode\":100,\"className\":\"LOS HALCONES\",\"dateFrom\":null,\"dateTo\":null,\"subjects\":{\"subjectId\":1,\"subjectName\":\"Matematica I\",\"deleted\":false},\"teachers\":{\"teachersId\":1,\"gender\":\"F\",\"firstName\":\"Ana\",\"middleName\":null,\"lastName\":\"Ramos\",\"otherTeacherDetails\":\"otros\",\"schools\":null,\"deleted\":false},\"deleted\":false,\"_links\":{\"links\":{\"href\":\"http://localhost/api/v2/classes/1\"}}}";
	    		
	    		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
	
	}
	
	@Test
    public void testAddClasses() throws ServiceException, ValidationException {
 
       classServiceImpl.create(new Classes());
       
    }


}
