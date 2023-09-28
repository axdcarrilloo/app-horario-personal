package com.casa.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DiaEntityTest {

    @Test
    void validarCaracteristicas() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
        MesEntity mes = new MesEntity(13L, anno, 3, "Enero", fechaCreacion, fechaCreacion);
        SemanaEntity semana = new SemanaEntity(2L, mes, 3, "3ra", fechaCreacion, fechaCreacion);
        DiaEntity dia = new DiaEntity(11L, semana, "Martes", 7, fechaCreacion, fechaCreacion);

        assertEquals(11L, dia.getId());
        assertEquals("Martes", dia.getNombre());
        assertEquals(7, dia.getHoras());
    }

}
