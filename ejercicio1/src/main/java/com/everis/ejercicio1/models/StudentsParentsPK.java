package com.everis.ejercicio1.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor	
public class StudentsParentsPK implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @ManyToOne
	 @JoinColumn(name = "student_id", nullable = false)
	 private Students students;
	
	 @ManyToOne
	 @JoinColumn(name = "parent_id", nullable = false)
	 private Parents parents;
	 

}
