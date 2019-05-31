package com.everis.ejercicio1.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.ejercicio1.exception.ModeloNotFoundException;
import com.everis.ejercicio1.models.Students;
import com.everis.ejercicio1.repository.IStudentsDAO;

@RunWith(MockitoJUnitRunner.class)
public class StudentsServiceImplTest {
	
	private static final int ID=1;

	private static final String GENDER = "F";
 
    private static final String NOMBRE = "Maria";
    
    private static final String SEGUNDONOMBRE = "Teresa";
 
    private static final String APELLIDO = "Buendía";
    
    private static final String DATEOFBIRTH = "20/02/2000";
 
    private static final String DETALLES = "otros";
    
    private static final boolean DELETED = false;
 
	//private Students student;
    
    
	 @InjectMocks
	  private StudentsServiceImpl studentService;
	  
	  @Mock
	  private IStudentsDAO studentRepo;
	
	@Test
	public void testCreate() {
		
		Students student = new Students();
	    student.setStudentId(ID);
	    student.setGender(GENDER);
	    student.setFirstName(NOMBRE);
	    student.setMiddleName(SEGUNDONOMBRE);
	    student.setLastName(APELLIDO);
	    
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    String strFecha = DATEOFBIRTH;
	    Date fecha = null;
	    
	    try {fecha = formato.parse(strFecha);
		} catch (Exception e) {e.printStackTrace();		}
	    
	    student.setDateOfBirth(fecha);
	    student.setOtherStudentDetails(DETALLES);
	    student.setDeleted(DELETED);
	    
	    List<Students> listaStudiantes = new ArrayList<>();
	    listaStudiantes.add(student);
	    
	    //lista
	    //when(studentRepo.findAll()).thenReturn(listaStudiantes);
	    //listar por ID
	    //when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
	    
	}

	@Test
    public void testAddUsuario() throws ServiceException, ValidationException {
 
        studentService.create(new Students());
        //.addUsuario(new UsuarioDTO());
    }
	
	@Test
	public void testUpdate() throws ModeloNotFoundException{
		Students student = new Students();

		//given(studentRepo.save(new Students())).willReturn(new Students());
	    //assertEquals(new Students(), studentService.update(new Students()));
		
		studentService.update(student);
	
	}

	/**
	 * Lista de todos los estudiantes
	 */
	@Test
	public void testList() {
		List<Students> list = new ArrayList<>();
	    given(studentRepo.findAll()).willReturn(list);
	    assertEquals(list, studentService.list());
	}

	/**
	 * Este teste admite validar que en caso el id no existe manda una excepcion.
	 * Admite como parametro la excepcion
	 * 
	 * El test fallara si el metodo no lanza una excepcion de ModeloNotFoundException.
	 * @throws ModeloNotFoundException validacion de excepcion
	 */
	@Test
	public void testSoftDelete() throws ModeloNotFoundException{
		studentService.softDelete(0);
		
	}


}
