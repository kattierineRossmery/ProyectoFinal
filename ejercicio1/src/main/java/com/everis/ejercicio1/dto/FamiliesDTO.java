package com.everis.ejercicio1.dto;

import org.springframework.hateoas.ResourceSupport;

import com.everis.ejercicio1.models.Parents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamiliesDTO extends ResourceSupport{
	 private int familyId;
	 private Parents parentsss;

}
