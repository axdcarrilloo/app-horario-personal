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
public class AnnoRegistrarDto {
	
	private Integer anno;
	
	private String nombre;

	private Boolean actual;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
