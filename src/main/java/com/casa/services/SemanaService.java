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
	
	@Autowired
	private MesService mesSvc;
	
	private Boolean validarCamposRegistrar(SemanaRegistrarDto semana) {
		log.info("MesService.class - validarCamposRegistrar() -> Validando campos vacios...!");
		if(semana.getMes() == null || semana.getMes().getId() == null) {
			return true;
		}
		if(semana.getNumeroSemanaMes() == null) {
			return true;
		}
		return semana.getNombre() == null;
	}
	
	public SemanaEntity consultarPorId(Long id) {
		log.info("SemanaService.class - consultarPorId() -> Consultando por Id una Semana..!");
		Optional<SemanaEntity> optional = semanaRepository.findById(id);
		return optional.orElse(null);
	}
	
	public List<SemanaEntity> consultarTodos() {
		log.info("SemanaService.class - consultarTodos() -> Consultando todas las Semanas...!");
		return semanaRepository.findAll();
	}
	
	public Map<String, Object> registrar(SemanaRegistrarDto semana) {
		log.info("SemanaService.class - registrar() -> Registrando Semana...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistrar(semana))) {
			map.put("errorCamposVacios", MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(mesSvc.consultarPorId(semana.getMes().getId()) == null) {
			map.put("errorMesVacio", MensajesProperties.MSG_NO_EXISTENTE);

		} else {
			semana.setFechaModificacion(Constantes.consultarFechaActual());
			semana.setFechaRegistro(Constantes.consultarFechaActual());
			map.put("respuesta", semanaRepository.save(SemanaMapper.convertirDtoAEntity(semana)).getId());
		}
		return map;
	}

}
