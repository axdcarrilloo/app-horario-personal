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
public class DiaModificarDto {
	
	private Long id;
	
	private String nombre;
	
	private Integer horas;
	
	private LocalDateTime fechaModificacion;

}
