package com.casa.domain.mappers;

import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.HorarioEntity;
import com.casa.utils.Constantes;

public class HorarioMapper {

    private HorarioMapper() {}

    public static HorarioEntity convertirDtoAEntity(HorarioRegistroDto horario) {
        return new HorarioEntity(0L, horario.getMateria(), horario.getProfesor(),horario.getIdCurso(),
                Constantes.consultarFechaActual(), Constantes.consultarFechaActual());
    }

}
