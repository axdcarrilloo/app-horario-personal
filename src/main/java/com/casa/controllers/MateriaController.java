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

import com.casa.domain.dtos.MateriaRegistroDto;
import com.casa.domain.dtos.MateriamodificarDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.MateriaService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.MATERIA, produces = MediaType.APPLICATION_JSON_VALUE)
public class MateriaController {
	
	@Autowired
	private MateriaService materiaSvc;
	
	@PutMapping(value = Route.MODIFICAR)
	public ResponseEntity<RespuestaPrincipalDto> modiicar(@RequestBody MateriamodificarDto materia) {
		Map<String, Object> map = materiaSvc.modificar(materia);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		String errorNoExistente = (String)map.get("errorNoExistente");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		} 
		if(errorNoExistente != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorNoExistente), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get("respuesta")), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = Route.CONSULTAR)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, 
				materiaSvc.consultarTodas()), HttpStatus.OK);
	}

	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody MateriaRegistroDto materia) {
		Map<String, Object> map = materiaSvc.registrar(materia);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get("respuesta")), HttpStatus.CREATED);
		}
	}
		
}
