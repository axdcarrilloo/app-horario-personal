package com.casa.services;

import com.casa.domain.entities.HorasDiaCursoEntity;
import com.casa.repositories.HorasDiaCursoRepository;
import com.casa.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorasDiaCursoService {

    private final Logger log = LoggerFactory.getLogger(HorasDiaCursoService.class);

    @Autowired
    private HorasDiaCursoRepository horasDiaCursoRepository;

    @Autowired
    private DiaService diaSvc;

    public Integer sumaHorasDia(Long idDia) {
        log.info("HorasDiaCursoService.class - sumaHorasDia() -> Sumando cantidad de horas por dia...!");
        int horasSumadas = 0;
        List<HorasDiaCursoEntity> horasCursos = consultarPorDia(idDia);
        for (HorasDiaCursoEntity horasDia : horasCursos){
            horasSumadas = horasSumadas + horasDia.getCantidadHoras();
        }
        return horasSumadas;
    }

    public List<HorasDiaCursoEntity> consultarPorDia(Long idDia) {
        log.info("HorasDiaCursoService.class - consultarPorDia() -> Consultando cantidad de horas por dia...!");
        return horasDiaCursoRepository.findByIdDia(idDia);
    }

    public Map<String, Object> registrar(HorasDiaCursoEntity horasDiaCurso) {
        log.info("HorasDiaCursoService.class - registrar() -> Registrando cantidad de horas al dia...!");
        Map<String, Object> map = new HashMap<>();
        if(!diaSvc.validarExistenciaPorId(horasDiaCurso.getDia().getId())) {
            map.put("errorDiaVacio", Constantes.MSG_NO_EXISTENTE);
        }else {
            map.put(Constantes.MAP_RESPUESTA, horasDiaCursoRepository.save(horasDiaCurso));
        }
        return map;
    }

    public Map<String, Object> agregarCantidadHoras(HorasDiaCursoEntity horasDiaCurso) {
        log.info("HorasDiaCursoService.class - agregarCantidadHoras() -> Agregando cantidad de horas al dia...!");
        Map<String, Object> map = new HashMap<>();
        map.put(Constantes.MAP_RESPUESTA,
                horasDiaCursoRepository.agregarCantidadHoras(horasDiaCurso.getId(), horasDiaCurso.getCantidadHoras(), Constantes.consultarFechaActual()));
        return map;
    }

}
