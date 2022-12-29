package com.casa.domain.mappers;

import com.casa.domain.dtos.MateriaRegistroDto;
import com.casa.domain.entities.MateriaEntity;

public class MateriaMapper {
	
	private MateriaMapper() {}
	
	public static MateriaEntity convertirDtoAEntity(MateriaRegistroDto materia) {
		return new MateriaEntity(0L, materia.getNombre(), materia.getFechaRegistro(), materia.getFechaModificacion());
	}

}
