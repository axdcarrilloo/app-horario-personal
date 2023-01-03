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

import com.casa.domain.dtos.DiaModificarDto;
import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.DiaService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.DIA, produces = MediaType.APPLICATION_JSON_VALUE)
public class DiaController {
	
	@Autowired
	private DiaService diaSvc;
	
	@PutMapping(value = Route.MODIFICAR)
	public ResponseEntity<RespuestaPrincipalDto> modificar(@RequestBody DiaModificarDto dia) {
		Map<String, Object> map = diaSvc.modificar(dia);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		String errorSemanaVacia = (String)map.get("errorSemanaVacia");
		String errorNoExistente = (String)map.get("errorNoExistente");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		} 
		if(errorSemanaVacia != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_MODIFICACION_FALLIDA, errorSemanaVacia), HttpStatus.NOT_FOUND);
		}
		if(errorNoExistente != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_MODIFICACION_FALLIDA, errorNoExistente), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_MODIFICACION_EXITOSA, map.get("respuesta")), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = Route.CONSULTAR_TOS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, 
				diaSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody DiaRegistroDto dia) {
		Map<String, Object> map = diaSvc.registrar(dia);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		String errorSemanaVacia = (String)map.get("errorSemanaVacia");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		}
		if(errorSemanaVacia != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorSemanaVacia), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get("respuesta")), HttpStatus.CREATED);
		}
	}

}
