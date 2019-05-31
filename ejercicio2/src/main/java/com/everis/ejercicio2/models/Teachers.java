package com.everis.ejercicio2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Teachers.
 * @author kvilcave
 * @version 27-05-2019 v1
 */
@ApiModel("Model Teachers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teachers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id de Teacher", required = true)
	private int teachersId;
	
	@NotBlank
	@ApiModelProperty(value = "Genero", required = true)
	private String gender;
	
	@NotBlank
	@ApiModelProperty(value = "Primer nombre debe tener minimo 3 caracteres", required = true)
	@Size(min=3, max =100, message = "Primer nombre debe tener minimo 3 caracteres")
	private String firstName;
	
	@NotBlank
	@ApiModelProperty(value = "Segundo nombre debe tener minimo 3 caracteres", required = true)
	@Size(min=3, max =100, message = "Segundo nombre debe tener minimo 3 caracteres")
	private String middleName;
	
	@NotBlank
	@ApiModelProperty(value = "Apellido debe tener minimo 3 caracteres", required = true)
	@Size(min=3,max=100, message = "Apellido debe tener minimo 3 caracteres")
	private String lastName;
	 
	@ApiModelProperty(value = "the parent's details", required = true)
	@Size(max=180)
	private String otherTeacherDetails;
	
	/**
	   * La entidad Teachers esta en relacion Many to One con la entidad Schoools.
	   * "schools" con el nombre de la columna "school_id" es referenciado
	   * al id(school_id) de la entidad Schools.
	   */
	@ApiModelProperty(value = "id de Schools", required = true)
	@ManyToOne
	@JoinColumn(name = "school_id")
	private Schools schools;

	private Boolean deleted;
}
