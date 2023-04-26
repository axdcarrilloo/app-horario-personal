package com.casa.domain.mappers;

import com.casa.domain.dtos.HorarioMostrarSimple;
import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.HorarioEntity;

import java.util.ArrayList;
import java.util.List;

public class HorarioMapper {

    private HorarioMapper() {}

    public static List<HorarioMostrarSimple> convertirListEntityToMostrarSimple(List<HorarioEntity> horarioEntities) {
        List<HorarioMostrarSimple> horariosSimples = new ArrayList<>();
        for(HorarioEntity horario : horarioEntities) {
            horariosSimples.add(new HorarioMostrarSimple(horario.getDia().getNombre(), horario.getMateria().getNombre(), horario.getHorasDictar()));
        }
        return horariosSimples;
    }

    public static HorarioEntity convertirDtoAEntity(HorarioRegistroDto horario) {
        return new HorarioEntity(0L, horario.getDia(), horario.getMateria(), horario.getProfesor(),
                horario.getHorasDictar(), horario.getFechaRegistro(), horario.getFechaModificacion());
    }

}
