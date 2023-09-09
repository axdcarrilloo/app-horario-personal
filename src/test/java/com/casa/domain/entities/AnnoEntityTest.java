package com.casa.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AnnoEntityTest {

    @Test
    void validarCaracteristicas() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoEntity anno = new AnnoEntity(12L, 2010, "2010", false, fechaCreacion, fechaCreacion);

        Assertions.assertEquals(12L, anno.getId());
        Assertions.assertFalse(anno.getActual());
    }

}
