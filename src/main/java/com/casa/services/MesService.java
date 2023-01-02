package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.MesRegistrarDto;
import com.casa.domain.entities.MesEntity;
import com.casa.domain.mappers.MesMapper;
import com.casa.repositories.MesRepository;
import com.casa.utils.Constantes;

@Service
public class MesService {

	private final Logger log = LoggerFactory.getLogger(MesService.class);
	
	@Autowired
	private MesRepository mesRepository;
	
	private Boolean validarCamposRegistrar(MesRegistrarDto mes) {
		log.info("MesService.class - validarCamposRegistrar() -> Validando campos vacios...!");
		if(mes.getAnno() == null || mes.getAnno().getId() == null) {
			return true;
		}
		if(mes.getNumeroMesAnno() == null) {
			return true;
		}
		return mes.getNombre() == null;
	}
	
	public List<MesEntity> consultarTodos() {
		log.info("MesService.class - consultarTodos() -> Consultando todos los Meses...!");
		return mesRepository.findAll();
	}
	
	public Map<String, Object> registrar(MesRegistrarDto mes) {
		log.info("MesService.class - registrar() -> Registrando Mes...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistrar(mes))) {
			map.put("errorCamposVacios", Constantes.MSG_CAMPOS_VACIOS);
		} else {
			mes.setFechaModificacion(Constantes.consultarFechaActual());
			mes.setFechaRegistro(Constantes.consultarFechaActual());
			map.put("respuesta", mesRepository.save(MesMapper.convertirDtoAEntity(mes)).getId());
		}		
		return map;
	}
	
}
