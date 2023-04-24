package com.casa.services;

import com.casa.domain.entities.AnnoEntity;
import com.casa.domain.entities.HorarioEntity;
import com.casa.repositories.AnnoRepository;
import com.casa.repositories.HorarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    private final Logger log = LoggerFactory.getLogger(HorarioService.class);

    @Autowired
    private HorarioRepository horarioRepository;

    public List<HorarioEntity> consultarTodos() {
        log.info("HorarioService.class - consultarTodos() -> Consultando todos los Horarios...!");
        return horarioRepository.findAll();
    }

}
