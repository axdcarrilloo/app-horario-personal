package com.casa.repositories;

import com.casa.domain.entities.HorasDiaCursoEntity;
import com.casa.utils.ConstantesSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface HorasDiaCursoRepository extends JpaRepository<HorasDiaCursoEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = ConstantesSQL.AGREGAR_CANTIDAD_HORAS, nativeQuery = true)
    Integer agregarCantidadHoras(@Param("id")Long id, @Param("cantidadHoras")Integer cantidadHoras, @Param("fechaModificacion") LocalDateTime fechaModificacion);

}
