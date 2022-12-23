package com.casa.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.DiaService;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.DIA, produces = MediaType.APPLICATION_JSON_VALUE)
public class DiaController {
	
	@Autowired
	private DiaService diaSvc;
	
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody DiaRegistroDto dia) {
		Map<String, Object> map = diaSvc.registrar(dia);
		Long respuesta = (Long)map.get("respuesta");
		if(respuesta != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto("Registro Exitoso", respuesta), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto("Registro Fallido", 0L), HttpStatus.BAD_REQUEST);
		}
	}

}
