package com.casa.domain.mappers;

import com.casa.domain.dtos.AnnoRegistrarDto;
import com.casa.domain.entities.AnnoEntity;

public class AnnoMapper {
	
	private AnnoMapper() {}
	
	public static AnnoEntity convertirDtoAEntity(AnnoRegistrarDto anno) {
		return new AnnoEntity(0L, anno.getAnno(), anno.getNombre(), anno.getActual(), anno.getFechaRegistro(), anno.getFechaModificacion());
	}

}
