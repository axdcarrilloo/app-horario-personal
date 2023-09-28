package com.casa.controllers;

import com.casa.utils.MensajesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.AnnoRegistrarDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.AnnoService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

import java.util.Map;

@RestController
@RequestMapping(value = Route.ANNO, produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnoController {
	
	@Autowired
	private AnnoService annoSvc;
	
	@GetMapping(value = Route.CONSULTAR_TOS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
				annoSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody AnnoRegistrarDto anno) {
		Map<String, Object> map = annoSvc.registrar(anno);
		String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOSVACIOS);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO,
					errorCamposVacios), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_EXITOSO,
					map.get(Constantes.MAP_RESPUESTA)), HttpStatus.CREATED);
		}
	}

}
