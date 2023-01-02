package com.casa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.domain.dtos.SemanaRegistrarDto;
import com.casa.services.SemanaService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.SEMANA, produces = MediaType.APPLICATION_JSON_VALUE)
public class SemanaController {
	
	@Autowired
	private SemanaService semanaSvc;
	
	@GetMapping(value = Route.CONSULTAR_TAS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA,
				semanaSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody SemanaRegistrarDto semana) {
		return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO,
				semanaSvc.registrar(semana)), HttpStatus.CREATED);
	}

}
