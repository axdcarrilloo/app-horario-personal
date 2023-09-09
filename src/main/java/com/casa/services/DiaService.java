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

import com.casa.domain.dtos.DiaModificarDto;
import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.entities.DiaEntity;
import com.casa.domain.mappers.DiaMapper;
import com.casa.repositories.DiaRepository;
import com.casa.utils.Constantes;

@Service
public class DiaService {
	
	private final Logger log = LoggerFactory.getLogger(DiaService.class);
	
	@Autowired
	private DiaRepository diaRepository;
	
	@Autowired
	private SemanaService semanaSvc;
	
	private Boolean validarCamposRegistrar(DiaRegistroDto dia) {
		log.info("DiaService.class - validarCamposRegistrar() -> Validando campos vacios...!");
		if(dia.getSemana() == null || dia.getSemana().getId() == null) {
			return true;
		}
		return dia.getNombre() == null;
	}
	
	private Boolean validarCamposModificar(DiaModificarDto dia) {
		log.info("DiaService.class - validarCamposModificar() -> Validando campos vacios...!");
		if(dia.getId() == null) {
			return true;
		}
		if(dia.getSemana() == null || dia.getSemana().getId() == null) {
			return true;
		}
		return dia.getNombre() == null;
	}

	public DiaEntity consultarPorId(Long id) {
		log.info("DiaService.class - consultarPorId() -> Consultado por Id un dia");
		Optional<DiaEntity> optional = diaRepository.findById(id);
		return optional.orElse(null);
	}
	
	public Map<String, Object> modificar(DiaModificarDto dia) {
		log.info("DiaService.class - modificar() -> Modificando dia");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposModificar(dia))) {
			map.put(Constantes.MAP_ERROR_CAMPOSVACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(semanaSvc.consultarPorId(dia.getSemana().getId()) == null) {
			map.put("errorSemanaVacia", MensajesProperties.MSG_NO_EXISTENTE);
			return map;
		}
		if(consultarPorId(dia.getId()) == null) {
			map.put(Constantes.MAP_ERROR_NOEXISTENTE, MensajesProperties.MSG_NO_EXISTENTE);
		} else {
			map.put(Constantes.MAP_RESPUESTA, diaRepository.modificarDia(dia.getId(), dia.getNombre(), Constantes.consultarFechaActual()));
		}
		return map;
	}
	
	public List<DiaEntity> consultarTodos() {
		log.info("DiaService.class - consultarTodos() -> Consultando todos los dias");
		return diaRepository.findAll();
	}
	
	public Map<String, Object> registrar(DiaRegistroDto dia) {
		log.info("DiaService.class - registrar() -> Registrando dia");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistrar(dia))) {
			map.put(Constantes.MAP_ERROR_CAMPOSVACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(semanaSvc.consultarPorId(dia.getSemana().getId()) == null) {
			map.put("errorSemanaVacia", MensajesProperties.MSG_NO_EXISTENTE);
		} else {
			dia.setHoras(7);
			dia.setHorasAcumuladas(0);
			dia.setFechaModificacion(Constantes.consultarFechaActual());
			dia.setFechaRegistro(Constantes.consultarFechaActual());
			map.put(Constantes.MAP_RESPUESTA, diaRepository.save(DiaMapper.convertirDtoAEntity(dia)).getId());
		}
		return map;
	}

}
