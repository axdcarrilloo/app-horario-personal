package com.casa.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.CursoRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.CursoService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.CURSO, produces = MediaType.APPLICATION_JSON_VALUE)
public class CursoController {
	
	@Autowired
	private CursoService cursoSvc;
	
	@GetMapping(value = Route.CONSULTAR_TOS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodas() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, 
				cursoSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@GetMapping(value = Route.CONSULTARPOR_NOMBRE)
	public ResponseEntity<RespuestaPrincipalDto> consultarPorNombre(@PathVariable String nombre) {
		Map<String, Object> map = cursoSvc.consultarPorNombreMap(nombre);
		String errorNoExistente = (String)map.get(Constantes.MAP_NOEXISTENTE);
		if(errorNoExistente != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_FALIDA, errorNoExistente), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.OK);
		}
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody CursoRegistroDto curso) {
		Map<String, Object> map = cursoSvc.registrar(curso);
		String errorCamposVacios = (String)map.get(Constantes.MAP_CAMPOSVACIOS);
		String errorSiExiste = (String)map.get(Constantes.MAP_SIEXISTENTE);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		}
		if(errorSiExiste != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorSiExiste), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.CREATED);
		}
	}

}
