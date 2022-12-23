package com.casa.domain.mappers;

import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.entities.DiaEntity;

public class DiaMapper {
	
	private DiaMapper() {}
	
	public static DiaEntity convertirDtoAEntity(DiaRegistroDto dia) {
		return new DiaEntity(0L, dia.getNombre(), dia.getHoras(), dia.getFechaRegistro(), dia.getFechaModificacion());
	}

}
