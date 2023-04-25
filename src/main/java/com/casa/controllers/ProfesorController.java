package com.casa.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.ProfesorModificarDto;
import com.casa.domain.dtos.ProfesorRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.ProfesorService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.PROFESOR, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfesorController {
	
	@Autowired
	private ProfesorService profesorSvc;
	
	@PutMapping(value = Route.MODIFICAR)
	public ResponseEntity<RespuestaPrincipalDto> modificar(@RequestBody ProfesorModificarDto profesor) {
		Map<String, Object> map = profesorSvc.modificar(profesor);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		String errorNoExistente = (String)map.get("errorNoExistente");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		} 
		if(errorNoExistente != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorNoExistente), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = Route.CONSULTAR_TOS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, 
				profesorSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody ProfesorRegistroDto profesor) {
		Map<String, Object> map = profesorSvc.registrar(profesor);
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
