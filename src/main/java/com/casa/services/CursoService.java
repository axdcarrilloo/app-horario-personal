package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.CursoRegistroDto;
import com.casa.domain.entities.CursoEntity;
import com.casa.domain.mappers.CursoMapper;
import com.casa.repositories.CursoRepository;
import com.casa.utils.Constantes;

@Service
public class CursoService {
	
	private final Logger log = LoggerFactory.getLogger(CursoService.class);
	
	@Autowired
	private CursoRepository cursoRepository;
	
	private Boolean validarCamposRegistrar(CursoRegistroDto curso) {
		return curso.getNombre() == null;
	}
	
	public List<CursoEntity> consultarTodos() {
		log.info("CursoService.class - consultarTodos() -> Consultado todos los Cursos...!");
		return cursoRepository.findAll();
	}
	
	public Boolean existenciaPorNombre(String nombre) {
		return consultarPorNombre(nombre) != null;
	}
	
	public Map<String, Object> consultarPorNombreMap(String nombre) {
		log.info("CursoService.class - consultarPorNombre() -> Consultado por Nombre un Curso...!");
		Map<String, Object> map = new HashMap<>();
		Optional<CursoEntity> optional = cursoRepository.findByNombre(nombre);
		if(optional.isPresent()) {
			map.put(Constantes.MAP_RESPUESTA, optional.get());
		} else {
			map.put(Constantes.MAP_NOEXISTENTE, Constantes.MSG_NO_EXISTENTE);
		}
		return map;
	}
	
	public CursoEntity consultarPorNombre(String nombre) {
		log.info("CursoService.class - consultarPorNombre() -> Consultado por Nombre un Curso...!");
		Optional<CursoEntity> optional = cursoRepository.findByNombre(nombre);
		return optional.orElse(null);
	}
	
	public Map<String, Object> registrar(CursoRegistroDto curso) {
		log.info("CursoService.class - registrar() -> Registrando Curso...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposRegistrar(curso))) {
			map.put(Constantes.ERROR_CAMPOS_VACIOS, Constantes.MSG_CAMPOS_VACIOS);
			return map;
		} 
		if(Boolean.TRUE.equals(existenciaPorNombre(curso.getNombre()))) {
			map.put(Constantes.ERROR_SIEXISTE, Constantes.MSG_SI_EXISTENTE);
			return map;
		} else {
			curso.setFechaModificacion(Constantes.consultarFechaActual());
			curso.setFechaRegistro(Constantes.consultarFechaActual());
			map.put(Constantes.MAP_RESPUESTA, cursoRepository.save(CursoMapper.convertirDtoAEntity(curso)).getId());
			return map;
		}
	}

}
