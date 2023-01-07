package com.casa.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.ProfesorEntity;
import com.casa.utils.ConstantesSQL;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Long> {
	
	Optional<ProfesorEntity> findByCedula(String cedula);
	
	@Modifying
    @Transactional
    @Query(value = ConstantesSQL.MODIFICAR_PROFESOR, nativeQuery = true)
	Integer modificar(@Param("id")Long id, @Param("cedula")String cedula, @Param("nombres")String nombres, @Param("apellidos")String apellidos, 
			@Param("edad")Integer edad, @Param("celular")String celular, @Param("direccion")String direccion, @Param("email")String email, 
			@Param("fechaModificacion")LocalDateTime fechaModificacion);
	
}
