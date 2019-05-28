package com.everis.ejercicio2.dto;

import java.util.Date;
import java.util.List;

import com.everis.ejercicio2.models.Classes;
import com.everis.ejercicio2.models.Students;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentPorClassDTO {
	
	private Classes clases;
	private List<Students> lstStudents;
	private Date dateFrom;
	private Date dateTo;

}
