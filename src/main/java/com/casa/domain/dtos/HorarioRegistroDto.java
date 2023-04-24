package com.casa.domain.dtos;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.MateriaEntity;
import com.casa.domain.entities.ProfesorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioRegistroDto {

    private DiaEntity dia;

    private MateriaEntity materia;

    private ProfesorEntity profesor;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaModificacion;

}
