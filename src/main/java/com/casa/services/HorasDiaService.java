package com.casa.services;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorasDiaEntity;
import com.casa.repositories.HorasDiaRepository;
import com.casa.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorasDiaService {

    private final Logger log = LoggerFactory.getLogger(HorasDiaService.class);

    @Autowired
    private HorasDiaRepository horasDiaRepository;

    public Integer sumaHorasDia(DiaEntity dia) {
        log.info("HorasDiaService.class - sumaHorasDia() -> Sumando cantidad de horas por dia...!");
        int horasSumadas = 0;
        List<HorasDiaEntity> horasDia = consultarPorDia(dia);
        for (HorasDiaEntity horaDia : horasDia){
            horasSumadas = horasSumadas + horaDia.getCantidadHoras();
        }
        return horasSumadas;
    }

    public List<HorasDiaEntity> consultarPorDia(DiaEntity dia) {
        log.info("HorasDiaService.class - consultarPorDia() -> Consultando cantidad de horas por dia...!");
        return horasDiaRepository.findByDia(dia);
    }

    public Map<String, Object> registrar(HorasDiaEntity horasDiaCurso) {
        log.info("HorasDiaService.class - registrar() -> Registrando cantidad de horas al dia...!");
        Map<String, Object> map = new HashMap<>();
        map.put(Constantes.MAP_RESPUESTA, horasDiaRepository.save(horasDiaCurso));
        return map;
    }
}
