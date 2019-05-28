package com.everis.ejercicio1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Parents.
 * @version 15-05-2019 v1
 * @author kvilcave
 *
 */
@ApiModel("Model Parent")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Parents")
public class Parents {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "Id de Parents", required = true)
  private int parentId;

  @NotNull @NotBlank
  @ApiModelProperty(value = "Genero", required = true)
  private String gender;

  @NotNull @NotBlank
  @ApiModelProperty(value = "Primer nombre debe tener minimo 3 caracteres", required = true)
  @Size(min=3, max =100, message = "Primer nombre debe tener minimo 3 caracteres")
  private String firstName;

  @ApiModelProperty(value = "Segundo nombre debe tener minimo 3 caracteres", required = true)
  @Size(min=3, max=100, message = "Segundo nombre debe tener minimo 3 caracteres")
  private String middleName;

  @NotBlank
  @ApiModelProperty(value = "Apellido debe tener minimo 3 caracteres", required = true)
  @Size(min=3,max=100, message = "Apellido debe tener minimo 3 caracteres")
  private String lastName;

  @ApiModelProperty(value = "the parent's details", required = true)
  @Size(max=200)
  private String otherParentDetails;

}
