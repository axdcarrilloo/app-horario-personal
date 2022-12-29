package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.MateriaRegistroDto;
import com.casa.domain.dtos.MateriamodificarDto;
import com.casa.domain.entities.MateriaEntity;
import com.casa.domain.mappers.MateriaMapper;
import com.casa.repositories.MateriaRepository;
import com.casa.utils.Constantes;

@Service
public class MateriaService {
	
	private final Logger log = LoggerFactory.getLogger(MateriaService.class);
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	private Boolean validarCamposModificar(MateriamodificarDto materia) {
		Boolean validate = false;
		if(materia.getId() == null) {
			validate = true;
		}
		if(materia.getNombre() == null) {
			validate = true;
		}
		return validate;
	}
	
	private Boolean validarCamposRegistrar(MateriaRegistroDto materia) {
		return materia.getNombre() == null;
	}
	
	public MateriaEntity consultarPorId(Long id) {
		Optional<MateriaEntity> optional = materiaRepository.findById(id);
		return optional.orElse(null);
	}
	
	public Map<String, Object> modificar(MateriamodificarDto materia) {
		log.info("MateriaService.class - modificar() -> Modificando materia...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposModificar(materia))) {
			map.put("errorCamposVacios", Constantes.MSG_CAMPOS_VACIOS);
			return map;
		} 
		if(consultarPorId(materia.getId()) == null) {
			map.put("errorNoExistente", Constantes.MSG_NO_EXISTENTE);
			return map;
		} else {
			map.put("respuesta", materiaRepository.modificarDia(materia.getId(), materia.getNombre(), Constantes.consultarFechaActual()));
			return map;
		}	
	}
	
	public List<MateriaEntity> consultarTodas() {
		log.info("MateriaService.class - consultarTodas() -> Consultando todas las materias...!");
		return materiaRepository.findAll();
	}
	
	public Map<String, Object> registrar(MateriaRegistroDto materia) {
		log.info("MateriaService.class - registrar() -> Registrando materia...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistrar(materia))) {
			map.put("errorCamposVacios", Constantes.MSG_CAMPOS_VACIOS);
		} else {
			materia.setFechaRegistro(Constantes.consultarFechaActual());
			materia.setFechaModificacion(Constantes.consultarFechaActual());
			map.put("respuesta", materiaRepository.save(MateriaMapper.convertirDtoAEntity(materia)).getId());
		}		
		return map;
	}
	
}
