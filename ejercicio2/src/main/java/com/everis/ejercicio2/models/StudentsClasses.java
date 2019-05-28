package com.everis.ejercicio2.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad StudentsClasses.
 * Es la entidad que se forma de la relación
 * de Many to Many entre Students y Classes.
 * @author kvilcave
 * @version 27-05-2019 v1
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(StudentsClassesPK.class)
public class StudentsClasses {
	
	//Muchos a uno, instancia a Students
	 @Id
	 private Students students;
	//Muchos a uno, instancia a Classes
	 @Id 
	 private Classes classes;
	  
	 @Id 
	 @Temporal(TemporalType.DATE)
	 @ApiModelProperty(value = "Fecha de inicio", required = true)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota") 
	 private Date dateFrom;
	  
	 @NotNull
	 @Temporal(TemporalType.DATE)
	 @ApiModelProperty(value = "Fecha fin", required = true)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota") 
	 private Date dateTo;
	  
	 
}
