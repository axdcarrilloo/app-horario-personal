package com.casa.domain.mappers;

import com.casa.domain.dtos.CursoRegistroDto;
import com.casa.domain.entities.CursoEntity;

public class CursoMapper {
	
	private CursoMapper() {}
	
	public static CursoEntity convertirDtoAEntity(CursoRegistroDto curso) {
		return new CursoEntity(0L, curso.getNombre(), curso.getFechaRegistro(), curso.getFechaModificacion());
	}

}
