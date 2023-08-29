package com.casa.services;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorarioEntity;
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

    public List<HorasDiaEntity> consultarPorHorario(HorarioEntity horario) {
        log.info("HorasDiaService.class - consultarPorDia() -> Consultando Horas por Dia...!");
        return horasDiaRepository.findByHorario(horario);
    }

    public List<HorasDiaEntity> consultarPorDia(DiaEntity dia) {
        log.info("HorasDiaService.class - consultarPorDia() -> Consultando Horas por Dia...!");
        return horasDiaRepository.findByDia(dia);
    }

    public List<HorasDiaEntity> consultarPorHorarioYDia(HorarioEntity horario, DiaEntity dia) {
        log.info("HorasDiaService.class - consultarPorHorario() -> Consultando cantidad de horas por Horario y Dia...!");
        return horasDiaRepository.findByHorarioAndDia(horario, dia);
    }

    public Map<String, Object> registrar(HorasDiaEntity horasDia) {
        log.info("HorasDiaService.class - registrar() -> Registrando cantidad de horas al dia...!");
        Map<String, Object> map = new HashMap<>();
        map.put(Constantes.MAP_RESPUESTA, horasDiaRepository.save(horasDia));
        return map;
    }
}
