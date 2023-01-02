package com.casa.domain.dtos;

import java.time.LocalDateTime;

import com.casa.domain.entities.SemanaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaRegistroDto {
	
	private SemanaEntity semana;
	
	private String nombre;
	
	private Integer horas;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
