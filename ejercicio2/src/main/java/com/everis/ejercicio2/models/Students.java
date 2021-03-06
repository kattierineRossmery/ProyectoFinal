package com.everis.ejercicio2.models;
 
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Students.
 * @author kvilcave
 * @version 15-05-2019 v1
 */
@ApiModel("Model Students")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Students")
public class Students {

  @Id
  @NotNull
  @ApiModelProperty(value = "the student's id", required = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentId;

  @NotBlank
  @ApiModelProperty(value = "the gender", required = true)
  private String gender;

  @NotBlank
  @ApiModelProperty(value = "the student first name", required = true)
  @Size(min=3,max=180, message = "Primer nombre debe tener minimo 3 caracteres")
  private String firstName;

  @ApiModelProperty(value = "the student middle name", required = true)
  @Size(min=3,max=180, message = "Segundo nombre debe tener minimo 3 caracteres")
  private String middleName;

  @NotNull
  @ApiModelProperty(value = "the student's last name", required = true)
  @Size(min=3,max=180, message = "Apellido debe tener minimo 3 caracteres")
  private String lastName;

  @NotNull
  @Past
  @Temporal(TemporalType.DATE)
  @ApiModelProperty(value = "the student's date of birth", required = true)
  private Date dateOfBirth;

  @ApiModelProperty(value = "the student's details", required = true)
  @Size(max=250)
  private String otherStudentDetails;

  private Boolean deleted;
}
