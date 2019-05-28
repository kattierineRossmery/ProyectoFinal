package com.everis.ejercicio2.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
	private Date timestamp;
	private String mensaje;
	private String detalles;
	

}
