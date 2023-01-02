package com.casa.domain.dtos;

import java.time.LocalDateTime;

import com.casa.domain.entities.AnnoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MesRegistrarDto {
	
	private AnnoEntity anno;
	
	private Integer numeroMesAnno;
	
	private String nombre;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
