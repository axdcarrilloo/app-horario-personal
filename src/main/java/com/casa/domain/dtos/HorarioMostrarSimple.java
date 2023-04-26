package com.casa.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioMostrarSimple {

    private String diaNombre;
    private String materiaNombre;
    private Integer horasDictar;

}
