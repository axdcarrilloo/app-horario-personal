package com.casa.domain.mappers;

import com.casa.domain.dtos.MesRegistrarDto;
import com.casa.domain.entities.MesEntity;

public class MesMapper {
	
	private MesMapper() {}

	public static MesEntity convertirDtoAEntity(MesRegistrarDto mes) {
		return new MesEntity(0L, mes.getAnno(), mes.getNumeroMesAnno(), mes.getNombre(), mes.getFechaRegistro(), 
				mes.getFechaModificacion());
	}
	
}
