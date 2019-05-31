package com.everis.ejercicio2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Esta clase pertenece a la entidad Schools.
 * @author kvilcave
 * @version 27-05-2019 v1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	
	@NotBlank
	@Size(min=3, max=70, message = "Segundo nombre debe tener minimo 3 caracteres")
	private String schoolName;
	


}
