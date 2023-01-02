package com.casa.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.SemanaRegistrarDto;
import com.casa.domain.entities.SemanaEntity;
import com.casa.domain.mappers.SemanaMapper;
import com.casa.repositories.SemanaRepository;
import com.casa.utils.Constantes;

@Service
public class SemanaService {
	
private final Logger log = LoggerFactory.getLogger(SemanaService.class);
	
	@Autowired
	private SemanaRepository semanaRepository;
	
	public List<SemanaEntity> consultarTodos() {
		log.info("SemanaService.class - consultarTodos() -> Consultando todas las Semanas...!");
		return semanaRepository.findAll();
	}
	
	public Long registrar(SemanaRegistrarDto semana) {
		log.info("SemanaService.class - registrar() -> Registrando Semana...!");
		semana.setFechaModificacion(Constantes.consultarFechaActual());
		semana.setFechaRegistro(Constantes.consultarFechaActual());
		return semanaRepository.save(SemanaMapper.convertirDtoAEntity(semana)).getId();
	}

}
