package com.everis.ejercicio1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase pertenece a la entidad Families.
 * @version 15-05-2019 V1
 * @author Kattierine Vilca - kvilcave
 *
 */
@ApiModel("Model Families")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Families {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "the family member id", required = true)
  private int familyId;

  @NotNull
  @ApiModelProperty(value = "the family member name", required = true)
  @Size(min=3, max=180, message = "Bombre familiar debe tener minimo 3 caracteres")
  private String familyName;

  /**
   * La entidad Families esta en relacion Many to One con la entidad Parents.
   * "parentsss" con el nombre de la columna "head_of_family_parent_id" es referenciado
   * al id(parent_id) de la entidad Parents.
   */
  @NotNull
  @ApiModelProperty(value = "the family member parent id", required = true)
  @ManyToOne
  @JoinColumn(name = "head_of_family_parent_id")
  private Parents parentsss;

  private Boolean deleted;
}
