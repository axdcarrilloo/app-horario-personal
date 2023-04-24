package com.casa.controllers;

import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.HorarioService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Route.HORARIO, produces = MediaType.APPLICATION_JSON_VALUE)
public class HorarioController {

    @Autowired
    private HorarioService horarioSvc;

    @GetMapping(value = Route.CONSULTAR_TOS)
    public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
        return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA,
                horarioSvc.consultarTodos()), HttpStatus.OK);
    }

}
