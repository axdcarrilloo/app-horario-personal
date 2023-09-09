package com.casa.controllers;

import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.dtos.RespuestaPrincipalDto;
import com.casa.services.HorarioService;
import com.casa.utils.Constantes;
import com.casa.utils.MensajesProperties;
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

    @GetMapping(value = Route.CONSULTAR_TOS_SIMPLIFICADO)
    public ResponseEntity<RespuestaPrincipalDto> consultarTodosSimplificado() {
        return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
                horarioSvc.consultarTodosSimplificado()), HttpStatus.OK);
    }

    @GetMapping(value = Route.CONSULTARPOR_IDDIA)
    public ResponseEntity<RespuestaPrincipalDto> consultarPorDia(@PathVariable Long id) {
        Map<String, Object> map = horarioSvc.consultarPorDia(id);
        final String errorDiaVacio = (String)map.get("errorDiaVacio");
        if(errorDiaVacio != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorDiaVacio), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
                    map.get(Constantes.MAP_RESPUESTA)), HttpStatus.OK);
        }
    }

    @GetMapping(value = Route.CONSULTARPOR_IDCURSO)
    public ResponseEntity<RespuestaPrincipalDto> consultarPorCurso(@PathVariable Long id) {
        return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
                horarioSvc.consultarPorCurso(id)), HttpStatus.OK);
    }

    @DeleteMapping(Route.ELIMINAR_PORID)
    public ResponseEntity<RespuestaPrincipalDto> eliminar(@PathVariable Long id) {
        Map<String, Object> map = horarioSvc.eliminar(id);
        String errorNoExistente = (String)map.get(Constantes.MAP_ERROR_NOEXISTENTE);
        if(errorNoExistente != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_ELIMINACION_FALLIDA, errorNoExistente), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.OK);
        }
    }

    @PostMapping(value = Route.REGISTRAR)
    public ResponseEntity<RespuestaPrincipalDto> registrar(@RequestBody HorarioRegistroDto horario) {
        Map<String, Object> map = horarioSvc.registrar(horario);
        String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOSVACIOS);
        String errorDiaVacio = (String)map.get("errorDiaVacio");
        String errorHorasDias = (String)map.get("errorHorasDias");
        String errorHorasIngresar = (String)map.get("errorHorasIngresar");
        String errorMateriaVacia = (String)map.get("errorMateriaVacia");
        String errorProfesorVacio = (String)map.get("errorProfesorVacio");
        String errorDuplicidad = (String)map.get(Constantes.MAP_ERROR_DUPLICIDAD);
        String errorDuplicidadMateria = (String)map.get("errorDuplicidadMateria");
        if(errorCamposVacios != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
        }
        if(errorDiaVacio != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorDiaVacio), HttpStatus.NOT_FOUND);
        }
        if(errorHorasDias != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorHorasDias), HttpStatus.BAD_REQUEST);
        }
        if(errorHorasIngresar != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorHorasIngresar), HttpStatus.BAD_REQUEST);
        }
        if(errorMateriaVacia != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorMateriaVacia), HttpStatus.NOT_FOUND);
        }
        if(errorProfesorVacio != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorProfesorVacio), HttpStatus.NOT_FOUND);
        }
        if(errorDuplicidad != null){
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorDuplicidad), HttpStatus.BAD_REQUEST);
        }
        if(errorDuplicidadMateria != null) {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_FALLIDO, errorDuplicidadMateria), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPUESTA)), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = Route.CONSULTAR_TOS)
    public ResponseEntity<RespuestaPrincipalDto> consultarTodos() {
        return new ResponseEntity<>(new RespuestaPrincipalDto(MensajesProperties.TTL_CONSULTA_EXITOSA,
                horarioSvc.consultarTodos()), HttpStatus.OK);
    }

}
