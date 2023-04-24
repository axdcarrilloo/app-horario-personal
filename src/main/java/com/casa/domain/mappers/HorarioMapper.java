package com.casa.domain.mappers;

import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.HorarioEntity;

public class HorarioMapper {

    private HorarioMapper() {}

    public static HorarioEntity convertirDtoAEntity(HorarioRegistroDto horario) {
        return new HorarioEntity(0L, horario.getDia(), horario.getMateria(), horario.getProfesor(),
                horario.getFechaRegistro(), horario.getFechaModificacion());
    }

}
