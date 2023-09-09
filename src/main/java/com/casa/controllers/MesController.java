package com.casa.controllers;

import java.util.Map;

import com.casa.utils.MensajesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.MesRegistrarDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.MesService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.MES)
public class MesController {
	
	@Autowired
	private MesService mesSvc;
	
	@GetMapping(value = Route.CONSULTAR_TOS)
	public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
		return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
				mesSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody MesRegistrarDto mes) {
		Map<String, Object> map = mesSvc.registrar(mes);
		String errorCamposVacios = (String)map.get("errorCamposVacios");
		String errorAnnoVacio = (String)map.get("errorAnnoVacio");
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		} 
		if(errorAnnoVacio != null) {
			return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorAnnoVacio), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_EXITOSO, map.get("respuesta")), HttpStatus.CREATED);
		}
	}

}
