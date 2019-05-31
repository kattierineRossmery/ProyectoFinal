package com.everis.ejercicio2.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Classes.
 * @author kvilcave
 * @version 27-05-2019 v1
 */
@ApiModel("Model Classes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classes {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id de la Clase", required = true)
	private int classId;
	
	/**
	 * Codigo de Clase
	 */
	@NotNull
	@ApiModelProperty(value = "Codigo de Clase", required = true)
	@Column(length = 4)
	private int classCode;
	/**
	 * Nombre de Clase
	 */
	@NotNull
	@ApiModelProperty(value = "Nombre de Clase", required = true)
	@Size(min=5, message = "La clase debe tener minimo 5 caracteres")
	private String className;

	/**
	 * Fecha de inicio de la clase
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "Fecha de inicio de la clase", required = true)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota") 
	private Date dateFrom;
	
	/**
	 * Fecha fin de la clase
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "Fecha fin de la clase", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota") 
	private Date dateTo;
	
	/**
	   * La entidad Classes esta en relacion Many to One con la entidad Subjects.
	   * "subjects" con el nombre de la columna subject_id es referenciado
	   * al id(subject_is) de la entidad Subjects.
	   */
	@ApiModelProperty(value = "id de Asignatura", required = true)
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subjects subjects;
	
	/**
	   * La entidad Classes esta en relacion Many to One con la entidad Parents.
	   * "teachers" con el nombre de la columna "teacher_id" es referenciado
	   * al id(teacher_id) de la entidad Teachers.
	   */
	@ApiModelProperty(value = "id de Teacher", required = true)
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teachers teachers;
	
	/**
	 * flag de eliminación lógica
	 */
	@ApiModelProperty(value = "flag de eliminación lógica", required = true)
	private Boolean deleted;
	
}
