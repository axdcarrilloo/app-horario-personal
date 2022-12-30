package com.casa.domain.mappers;

import com.casa.domain.dtos.ProfesorRegistroDto;
import com.casa.domain.entities.ProfesorEntity;

public class ProfesorMapper {
	
	private ProfesorMapper() {}
	
	public static ProfesorEntity convertirDtoAEntity(ProfesorRegistroDto profesor) {
		return new ProfesorEntity(0L, profesor.getCedula(), profesor.getNombres(), profesor.getApellidos(), profesor.getEdad(),
				profesor.getCelular(), profesor.getDireccion(), profesor.getEmail(), profesor.getFechaRegistro(), profesor.getFechaModificacion());
	}

}
