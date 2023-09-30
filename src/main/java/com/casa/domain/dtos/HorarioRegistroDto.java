package com.casa.domain.dtos;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.MateriaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioRegistroDto {

    private DiaEntity dia;

    private MateriaEntity materia;

    private Long idProfesor;

    private Long idCurso;

    private Integer horasDictar;

}
