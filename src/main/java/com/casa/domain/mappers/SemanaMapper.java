package com.casa.domain.mappers;

import com.casa.domain.dtos.SemanaRegistrarDto;
import com.casa.domain.entities.SemanaEntity;

public class SemanaMapper {
	
	private SemanaMapper() {}
	
	public static SemanaEntity convertirDtoAEntity(SemanaRegistrarDto semana) {
		return new SemanaEntity(0L, semana.getMes(), semana.getNumeroSemanaMes(), semana.getNombre(), semana.getFechaRegistro(), 
				semana.getFechaModificacion());
	}

}
