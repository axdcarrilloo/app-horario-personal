package com.casa.domain.mappers;

import com.casa.domain.dtos.HorarioMostrarSimple;
import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.HorasDiaEntity;
import com.casa.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class HorarioMapper {

    private HorarioMapper() {}

    public static List<HorarioMostrarSimple> convertirEntityADto(List<HorasDiaEntity> horasDia) {
        List<HorarioMostrarSimple> horariosSimplificados = new ArrayList<>();
        for(HorasDiaEntity horaDia : horasDia) {
            horariosSimplificados.add(new HorarioMostrarSimple(horaDia.getDia().getNombre(), horaDia.getHorario().getIdCurso(), horaDia.getHorario().getMateria().getNombre(),
                    "", horaDia.getCantidadHoras()));
        }
        return horariosSimplificados;
    }

    public static HorarioEntity convertirDtoAEntity(HorarioRegistroDto horario) {
        return new HorarioEntity(0L, horario.getIdProfesor(), horario.getMateria(),horario.getIdCurso(),
                Constantes.consultarFechaActual(), Constantes.consultarFechaActual());
    }

}
