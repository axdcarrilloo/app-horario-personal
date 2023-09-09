package com.casa.services;

import com.casa.domain.entities.AnnoEntity;
import com.casa.repositories.AnnoRepository;
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
import java.util.Optional;

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
    void consultarTodosTestExito() {
        Mockito.when(annoRepository.findAll()).thenReturn(obtenerAnnos());

        List<AnnoEntity> annos = annoSvc.consultarTodos();

        Assertions.assertFalse(annos.isEmpty());
        Assertions.assertEquals(3, annos.size());
    }

    @Test
    void consultarPorIdTestExito() {
        Optional<AnnoEntity> optional = Optional.of(obtenerAnno());
        Mockito.when(annoRepository.findById(12L)).thenReturn(optional);

        AnnoEntity annoValidado = annoSvc.consultarPorId(12L);

        Assertions.assertEquals(12L, annoValidado.getId());
        Assertions.assertFalse(annoValidado.getActual());
        Assertions.assertEquals(2009, annoValidado.getFechaRegistro().getYear());
    }

}
