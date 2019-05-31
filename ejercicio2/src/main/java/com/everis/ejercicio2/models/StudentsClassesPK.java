package com.everis.ejercicio2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase contiene las llaves primarias
 *  de la Entidad StudentsClasses.
 * @author kvilcave
 *
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class StudentsClassesPK implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Relacion Many to One con Students.
	 */
	 @ManyToOne
	 @JoinColumn(name = "student_id", nullable = false)
	 private Students students;
	
	 /**
	  * Relacion Many to One con Classes.
	  */
	 @ManyToOne
	 @JoinColumn(name = "class_id", nullable = false)
	 private Classes classes;

	 private Date dateFrom;

}
