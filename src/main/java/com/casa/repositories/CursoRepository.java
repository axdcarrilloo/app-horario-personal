package com.casa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
	
	Optional<CursoEntity> findByNombre(String nombre);

}
