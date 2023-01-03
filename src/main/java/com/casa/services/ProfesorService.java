package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.ProfesorModificarDto;
import com.casa.domain.dtos.ProfesorRegistroDto;
import com.casa.domain.entities.ProfesorEntity;
import com.casa.domain.mappers.ProfesorMapper;
import com.casa.repositories.ProfesorRepository;
import com.casa.utils.Constantes;

@Service
public class ProfesorService {
	
	private final Logger log = LoggerFactory.getLogger(ProfesorService.class);
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	private Boolean validarCamposModificar(ProfesorModificarDto profesor) {
		log.info("ProfesorService.class - validarCamposModificar() -> Validando campos vacios...!");
		if(profesor.getId() == null) {
			return true;
		}
		if(profesor.getCedula() == null) {
			return true;
		}
		if(profesor.getNombres() == null) {
			return true;
		}
		if(profesor.getApellidos() == null) {
			return true;
		}
		if(profesor.getEdad() == null) {
			return true;
		}
		if(profesor.getCelular() == null) {
			return true;
		}
		if(profesor.getDireccion() == null) {
			return true;
		}
		return profesor.getEmail() == null;
	}
	
	private Boolean validarCamposRegistro(ProfesorRegistroDto profesor) {
		log.info("ProfesorService.class - validarCamposRegistro() -> Validando campos vacios...!");
		if(profesor.getCedula() == null) {
			return true;
		}
		if(profesor.getNombres() == null) {
			return true;
		}
		if(profesor.getApellidos() == null) {
			return true;
		}
		if(profesor.getEdad() == null) {
			return true;
		}
		if(profesor.getCelular() == null) {
			return true;
		}
		if(profesor.getDireccion() == null) {
			return true;
		}
		return profesor.getEmail() == null;
	}
	
	public Map<String, Object> modificar(ProfesorModificarDto profesor) {
		log.info("ProfesorService.class - modificar() -> Modificando profesor...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposModificar(profesor))) {
			map.put("errorCamposVacios", Constantes.MSG_CAMPOS_VACIOS);
			return map;
		} 
		if(consultarPorId(profesor.getId()) == null) {
			map.put("errorNoExistente", Constantes.MSG_NO_EXISTENTE);
			return map;
		} else {
			map.put("respuesta", profesorRepository.modificar(profesor.getId(), profesor.getCedula(), profesor.getNombres(), profesor.getApellidos(),
					profesor.getEdad(), profesor.getCelular(), profesor.getDireccion(), profesor.getEmail(), Constantes.consultarFechaActual()));
			return map;
		}	
	}
	
	public ProfesorEntity consultarPorId(Long id) {
		log.info("ProfesorService.class - consultarPorId() -> Consultando por Id un profesor...!");
		Optional<ProfesorEntity> optional = profesorRepository.findById(id);
		return optional.orElse(null);
	}
	
	public List<ProfesorEntity> consultarTodos() {
		log.info("ProfesorService.class - consultar() -> Consultando todos los profesores...!");
		return profesorRepository.findAll();
	}
	
	public Map<String, Object> registrar(ProfesorRegistroDto profesor) {
		log.info("ProfesorService.class - registrar() -> Registrando profesor...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistro(profesor))) {
			map.put("errorCamposVacios", Constantes.MSG_CAMPOS_VACIOS);
		} else {
			profesor.setFechaRegistro(Constantes.consultarFechaActual());
			profesor.setFechaModificacion(Constantes.consultarFechaActual());
			map.put("respuesta", profesorRepository.save(ProfesorMapper.convertirDtoAEntity(profesor)).getId());
		}		
		return map;
	}

}
