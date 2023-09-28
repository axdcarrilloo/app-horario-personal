package com.casa.services;

import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.entities.*;
import com.casa.domain.mappers.DiaMapper;
import com.casa.repositories.DiaRepository;
import com.casa.repositories.SemanaRepository;
import com.casa.utils.Constantes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiaServiceTest {

    @Mock
    private SemanaService semanaSvc;

    @Mock
    private DiaRepository diaRepository;

    @InjectMocks
    private DiaService diaSvc;

    private List<DiaEntity> obtenerDias() {
        List<DiaEntity> dias = new ArrayList<>();

        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
        MesEntity mes = new MesEntity(13L, anno, 3, "Enero", fechaCreacion, fechaCreacion);
        SemanaEntity semana = new SemanaEntity(2L, mes, 3, "3ra", fechaCreacion, fechaCreacion);
        DiaEntity dia1 = new DiaEntity(11L, semana, "Martes", 7, fechaCreacion, fechaCreacion);
        DiaEntity dia2 = new DiaEntity(13L, semana, "Miercoles", 7, fechaCreacion, fechaCreacion);
        DiaEntity dia3 = new DiaEntity(14L, semana, "Miercoles", 7, fechaCreacion, fechaCreacion);

        dias.add(dia1);
        dias.add(dia2);
        dias.add(dia3);
        return dias;
    }

    private DiaEntity obtenerDia() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
        MesEntity mes = new MesEntity(13L, anno, 3, "Enero", fechaCreacion, fechaCreacion);
        SemanaEntity semana = new SemanaEntity(2L, mes, 3, "3ra", fechaCreacion, fechaCreacion);
        return new DiaEntity(11L, semana, "Martes", 7, fechaCreacion, fechaCreacion);
    }

    @Test
    void consultarPorIdCasoErrorTest() {
        Optional<DiaEntity> optional = Optional.empty();
        when(diaRepository.findById(11L)).thenReturn(optional);

        DiaEntity dia = diaSvc.consultarPorId(11L);

        assertNull(dia);
    }

    @Test
    void consultarPorIdCasoExitoTest() {
        Optional<DiaEntity> optional = Optional.of(obtenerDia());
        when(diaRepository.findById(11L)).thenReturn(optional);

        DiaEntity dia = diaSvc.consultarPorId(11L);

        assertNotNull(dia);
        assertEquals(11L, dia.getId());
        assertEquals("Martes", dia.getNombre());
    }

    @Test
    void consultarTodosTest() {
        when(diaRepository.findAll()).thenReturn(obtenerDias());

        List<DiaEntity> dias = diaSvc.consultarTodos();

        assertFalse(dias.isEmpty());
        assertEquals(3, dias.size());
    }

    @Test
    void registrarCasoExitosoTest() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
        MesEntity mes = new MesEntity(13L, anno, 3, "Enero", fechaCreacion, fechaCreacion);
        SemanaEntity semana = new SemanaEntity(2L, mes, 3, "3ra", fechaCreacion, fechaCreacion);
        DiaRegistroDto diaDto = new DiaRegistroDto(semana, "Martes", 7 , 5, null, null);

        when(semanaSvc.consultarPorId(2L)).thenReturn(semana);
        when(diaRepository.save(any())).thenReturn(obtenerDia());

        Map<String, Object> map = diaSvc.registrar(diaDto);

        assertNotNull(map);
        assertNotNull(map.get(Constantes.MAP_RESPUESTA));
        assertEquals(11L, (Long)map.get(Constantes.MAP_RESPUESTA));
    }

}
