package com.casa.repositories;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorasDiaCursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasDiaCursoRepository extends JpaRepository<HorasDiaCursoEntity, Long> {

    List<HorasDiaCursoEntity> findByDia(DiaEntity dia);

}
