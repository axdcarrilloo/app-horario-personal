package com.casa.services;

import com.casa.domain.dtos.AnnoRegistrarDto;
import com.casa.domain.entities.AnnoEntity;
import com.casa.domain.mappers.AnnoMapper;
import com.casa.repositories.AnnoRepository;
import com.casa.utils.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnoServiceTest {

    @Mock
    private AnnoRepository annoRepository;

    @InjectMocks
    private AnnoService annoSvc;

    private AnnoEntity obtenerAnno() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        return new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);
    }

    private List<AnnoEntity> obtenerAnnos() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno1 = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);

        fechaCreacion = LocalDateTime.of(2009, 11, 23, 13, 20, 10);
        AnnoEntity anno2 = new AnnoEntity(13L, 2010, "2010", false, fechaCreacion, fechaCreacion);

        fechaCreacion = LocalDateTime.of(2009, 11, 24, 15, 1, 22);
        AnnoEntity anno3 = new AnnoEntity(14L, 2010, "2010", false, fechaCreacion, fechaCreacion);

        List<AnnoEntity> annos = new ArrayList<>();
        annos.add(anno1);
        annos.add(anno2);
        annos.add(anno3);
        return annos;
    }

    @Test
    void registrarCasoExito() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoRegistrarDto annoDto = new AnnoRegistrarDto(2019, "2019", false, fechaCreacion, fechaCreacion);
        when(annoRepository.save(any())).thenReturn(obtenerAnno());

        Map<String, Object> map = annoSvc.registrar(annoDto);

        assertNotNull(map);
        assertNotNull(map.get(Constantes.MAP_RESPUESTA));
        assertEquals(12L, (Long)map.get(Constantes.MAP_RESPUESTA));
    }

    @Test
    void consultarTodosTestExito() {
        when(annoRepository.findAll()).thenReturn(obtenerAnnos());

        List<AnnoEntity> annos = annoSvc.consultarTodos();

        Assertions.assertFalse(annos.isEmpty());
        Assertions.assertEquals(3, annos.size());
    }

    @Test
    void consultarPorIdTestError() {
        AnnoEntity annoVacio = new AnnoEntity();
        Optional<AnnoEntity> optional = Optional.of(annoVacio);
        when(annoRepository.findById(9L)).thenReturn(optional);

        AnnoEntity annoValidado = annoSvc.consultarPorId(9L);

        Assertions.assertNull(annoValidado.getId());
        Assertions.assertNull(annoValidado.getAnno());
        Assertions.assertNull(annoValidado.getNombre());
        Assertions.assertNull(annoValidado.getActual());
    }

    @Test
    void consultarPorIdTestExito() {
        Optional<AnnoEntity> optional = Optional.of(obtenerAnno());
        when(annoRepository.findById(12L)).thenReturn(optional);

        AnnoEntity annoValidado = annoSvc.consultarPorId(12L);

        Assertions.assertEquals(12L, annoValidado.getId());
        Assertions.assertFalse(annoValidado.getActual());
        Assertions.assertEquals(2009, annoValidado.getFechaRegistro().getYear());
    }

}
