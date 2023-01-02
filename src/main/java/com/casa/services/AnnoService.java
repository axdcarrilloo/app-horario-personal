package com.casa.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.AnnoRegistrarDto;
import com.casa.domain.entities.AnnoEntity;
import com.casa.domain.mappers.AnnoMapper;
import com.casa.repositories.AnnoRepository;
import com.casa.utils.Constantes;

@Service
public class AnnoService {
	
	private final Logger log = LoggerFactory.getLogger(AnnoService.class);
	
	@Autowired
	private AnnoRepository annoRepository;
	
	public List<AnnoEntity> consultarTodos() {
		log.info("AnnoService.class - consultarTodos() -> Consultando todos los Años...!");
		return annoRepository.findAll();
	}
	
	public Long registrar(AnnoRegistrarDto anno) {
		log.info("AnnoService.class - registrar() -> Registrando Año...!");
		anno.setFechaModificacion(Constantes.consultarFechaActual());
		anno.setFechaRegistro(Constantes.consultarFechaActual());
		return annoRepository.save(AnnoMapper.convertirDtoAEntity(anno)).getId();
	}

}
