package com.casa.domain.mappers;

import com.casa.domain.dtos.AnnoRegistrarDto;
import com.casa.domain.entities.AnnoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AnnoMapperTest {

    @Test
    void convertirDtoAEntityTest() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 22, 11, 12, 41);
        AnnoRegistrarDto annoRegistrar = new AnnoRegistrarDto(2009, "2009", false, fechaCreacion, fechaCreacion);

        AnnoEntity anno = AnnoMapper.convertirDtoAEntity(annoRegistrar);

        Assertions.assertEquals(0L, anno.getId());
        Assertions.assertFalse(anno.getActual());
        Assertions.assertEquals("2009", anno.getNombre());
    }

}
