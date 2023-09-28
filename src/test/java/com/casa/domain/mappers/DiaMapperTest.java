package com.casa.domain.mappers;

import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.entities.AnnoEntity;
import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.MesEntity;
import com.casa.domain.entities.SemanaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DiaMapperTest {

    @Test
    void convertirDtoAEntity() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
        MesEntity mes = new MesEntity(13L, anno, 3, "Enero", fechaCreacion, fechaCreacion);
        SemanaEntity semana = new SemanaEntity(2L, mes, 3, "3ra", fechaCreacion, fechaCreacion);
        DiaRegistroDto diaDto = new DiaRegistroDto(semana, "Martes", 7 , 5, fechaCreacion, fechaCreacion);

        DiaEntity dia = DiaMapper.convertirDtoAEntity(diaDto);

        assertEquals(0L, dia.getId());
        assertEquals("Martes", dia.getNombre());
    }

}
