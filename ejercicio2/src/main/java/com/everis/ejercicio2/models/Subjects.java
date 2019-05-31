package com.everis.ejercicio2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Subjects.
 * @author kvilcave
 * @version 27-05-2019 v1
 */
@ApiModel("Model Subjects")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subjects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id de Subject", required = true)
	private int subjectId;
	
	@NotBlank
	@ApiModelProperty(value = "Primer nombre debe tener minimo 3 caracteres", required = true)
	@Size(min=3, max =70, message = "Asignatura debe tener minimo 3 caracteres")
	private String subjectName;
	
	private Boolean deleted;

}
