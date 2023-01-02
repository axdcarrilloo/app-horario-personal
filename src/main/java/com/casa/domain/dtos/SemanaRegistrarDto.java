package com.casa.domain.dtos;

import java.time.LocalDateTime;

import com.casa.domain.entities.MesEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SemanaRegistrarDto {
	
	private MesEntity mes;
	
	private Integer numeroSemanaMes;
	
	private String nombre;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
