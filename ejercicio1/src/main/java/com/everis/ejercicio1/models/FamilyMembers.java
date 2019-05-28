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
 * Esta clase pertenece a la entidad FamilyMembers.
 * @version 15-05-2019 v1
 * @author kvilcave
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ApiModel("Model Family")
public class FamilyMembers {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "the family's id", required = true)
  private int familyMemberId;

  @ApiModelProperty(value = "Parent or student", required = true)
  @Size(max=10)
  private String parentOrStudentMembers;

  /**
   * La entidad FamilyMembers está en relación Many to One con la entidad Families.
   * "families" es refernciado al id(familyId) de la entidad Families.
   */
  @ManyToOne
  @ApiModelProperty(value = "the family's id", required = true)
  @JoinColumn(name = "family_id")
  private Families families;

  /**
   * La entidad FamilyMembers está en relacion Many to One a la entity Parents.
   * "parents" es referenciado a la id(parentId) en la entidad Parents.
   */
  @ManyToOne
  @ApiModelProperty(value = "the parent's id", required = true)
  @JoinColumn(name = "parent_id")
  private Parents parents;

  /**
   * La entidad FamilyMembers esta en relacion Many to One a la entidad Students.
   * "students" es refernciado a la id(studentId) en la entidad Students.
   */
  @ManyToOne
  @ApiModelProperty(value = "the student's id", required = true)
  @JoinColumn(name = "student_id")
  private Students students;

}
