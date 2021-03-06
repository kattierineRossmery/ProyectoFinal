package com.everis.ejercicio2.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.everis.ejercicio2.models.Students;
import com.everis.ejercicio2.models.Subjects;
import com.everis.ejercicio2.models.Teachers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ClassDTO {
	
	private int classId;
	private Subjects subjects;
	private Teachers teachers;
	private int classCode;
	private String className;
	private boolean deleted;
	private List<Students> lstStudents;
	private Date dateFrom;
	private Date dateTo;

}
