package com.casa.repositories;

import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Long> {

    List<HorarioEntity> findByMateriaAndIdCurso(MateriaEntity materia, Long idCurso);

    List<HorarioEntity> findByIdCurso(Long idCurso);

    List<HorarioEntity> findByIdProfesorAndMateriaAndIdCurso(Long idProfesor, MateriaEntity materia, Long idCurso);
}
