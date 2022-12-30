package com.casa.domain.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorRegistroDto {
	
	private String cedula;
	
	private String nombres;
	
	private String apellidos;
	
	private Integer edad;
	
	private String celular;
	
	private String direccion;
	
	private String email;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
