package com.casa.services;

import com.casa.domain.entities.DiaEntity;
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

    public Integer sumaHorasDia(DiaEntity dia) {
        log.info("HorasDiaCursoService.class - sumaHorasDia() -> Sumando cantidad de horas por dia...!");
        int horasSumadas = 0;
        List<HorasDiaCursoEntity> horasCursos = consultarPorDia(dia);
        for (HorasDiaCursoEntity horasDia : horasCursos){
            horasSumadas = horasSumadas + horasDia.getCantidadHoras();
        }
        return horasSumadas;
    }

    public List<HorasDiaCursoEntity> consultarPorDia(DiaEntity dia) {
        log.info("HorasDiaCursoService.class - consultarPorDia() -> Consultando cantidad de horas por dia...!");
        return horasDiaCursoRepository.findByDia(dia);
    }

    public Map<String, Object> registrar(HorasDiaCursoEntity horasDiaCurso) {
        log.info("HorasDiaCursoService.class - registrar() -> Registrando cantidad de horas al dia...!");
        Map<String, Object> map = new HashMap<>();
        map.put(Constantes.MAP_RESPUESTA, horasDiaCursoRepository.save(horasDiaCurso));
        return map;
    }
}
