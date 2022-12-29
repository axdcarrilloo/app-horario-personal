package com.casa.repositories;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.MateriaEntity;
import com.casa.utils.ConstantesSQL;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
	
	@Modifying
    @Transactional
    @Query(value = ConstantesSQL.MODIFICAR_MATERIA, nativeQuery = true)
	Integer modificarDia(@Param("id")Long id, @Param("nombre")String nombre, @Param("fechaModificacion")LocalDateTime fechaModificacion);

}
