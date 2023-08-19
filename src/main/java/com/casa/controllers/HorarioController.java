package com.casa.controllers;

import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.HorarioService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = Route.HORARIO, produces = MediaType.APPLICATION_JSON_VALUE)
public class HorarioController {

    @Autowired
    private HorarioService horarioSvc;

    @GetMapping(value = Route.CONSULTARPOR_IDDIA)
    public ResponseEntity<RespuestaPrincipalDto> consultarPorIdDia(@PathVariable Long id) {
        Map<String, Object> map = horarioSvc.consultarPorIdDia(id);
        String errorNoExistente = (String)map.get(Constantes.MAP_NOEXISTENTE);
        if(errorNoExistente != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_FALIDA, errorNoExistente), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(Route.ELIMINAR_PORID)
    public ResponseEntity<RespuestaPrincipalDto> eliminar(@PathVariable Long id) {
        Map<String, Object> map = horarioSvc.eliminar(id);
        String errorNoExistente = (String)map.get(Constantes.MAP_NOEXISTENTE);
        if(errorNoExistente != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_ELIMINACION_FALLIDA, errorNoExistente), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.OK);
        }
    }

    @PostMapping(value = Route.REGISTRAR)
    public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody HorarioRegistroDto horario) {
        Map<String, Object> map = horarioSvc.registrar(horario);
        String errorCamposVacios = (String)map.get(Constantes.MAP_CAMPOSVACIOS);
        String errorDiaVacio = (String)map.get("errorDiaVacio");
        String errorHorasDias = (String)map.get("errorHorasDias");
        String errorHorasIngresar = (String)map.get("errorHorasIngresar");
        String errorMateriaVacia = (String)map.get("errorMateriaVacia");
        String errorProfesorVacio = (String)map.get("errorProfesorVacio");
        if(errorCamposVacios != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
        }
        if(errorDiaVacio != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorDiaVacio), HttpStatus.NOT_FOUND);
        }
        if(errorHorasDias != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorHorasDias), HttpStatus.BAD_REQUEST);
        }
        if(errorHorasIngresar != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorHorasIngresar), HttpStatus.BAD_REQUEST);
        }
        if(errorMateriaVacia != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorMateriaVacia), HttpStatus.NOT_FOUND);
        }
        if(errorProfesorVacio != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_FALLIDO, errorProfesorVacio), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = Route.CONSULTAR_TOS)
    public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
        return new ResponseEntity<>(new RespuestaPrincipalDto(Constantes.TTL_CONSULTA_EXITOSA,
                horarioSvc.consultarTodos()), HttpStatus.OK);
    }

}
