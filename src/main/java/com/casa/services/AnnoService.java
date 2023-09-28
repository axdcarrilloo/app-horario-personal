package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.casa.utils.MensajesProperties;
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

	private Boolean validarCamposObligatoriosRegistrar(AnnoRegistrarDto annoDto) {
		return annoDto.getAnno() == null;
	}
	
	public AnnoEntity consultarPorId(Long id) {
		log.info("AnnoService.class - consultarPorId() -> Consultando por Id un Año...!");
		Optional<AnnoEntity> option = annoRepository.findById(id);
		return option.orElse(null);
	}
	
	public List<AnnoEntity> consultarTodos() {
		log.info("AnnoService.class - consultarTodos() -> Consultando todos los Años...!");
		return annoRepository.findAll();
	}
	
	public Map<String, Object> registrar(AnnoRegistrarDto anno) {
		Map<String, Object> map = new HashMap<>();
		if(validarCamposObligatoriosRegistrar(anno)) {
			map.put(Constantes.MAP_ERROR_CAMPOSVACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
		} else {
			log.info("AnnoService.class - registrar() -> Registrando Año...!");
			anno.setNombre(anno.getAnno().toString());
			anno.setFechaModificacion(Constantes.consultarFechaActual());
			anno.setFechaRegistro(Constantes.consultarFechaActual());
			map.put(Constantes.MAP_RESPUESTA,annoRepository.save(AnnoMapper.convertirDtoAEntity(anno)).getId());
		}
		return map;
	}

}
