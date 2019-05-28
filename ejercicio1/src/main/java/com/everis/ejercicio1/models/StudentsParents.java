package com.everis.ejercicio1.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad StudentsParents.
 * Es la entidad que se forma de la relación
 * de Many to Many entre Students y Parents.
 * @author kvilcave
 * @version 28-05-2019 v1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(StudentsParentsPK.class)
public class StudentsParents {
	
	//Muchos a uno, instancia a Students
		 @Id
		 private Students students;
		//Muchos a uno, instancia a Parents
		 @Id 
		 private Parents parents;

}
