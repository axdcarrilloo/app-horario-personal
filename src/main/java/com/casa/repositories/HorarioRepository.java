package com.casa.repositories;

import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.HorasDiaEntity;
import com.casa.domain.entities.MateriaEntity;
import com.casa.domain.entities.ProfesorEntity;
import com.casa.utils.ConstantesSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Long> {

    List<HorarioEntity> findByIdCurso(Long idCurso);

    HorarioEntity findByProfesorAndMateriaAndIdCurso(ProfesorEntity profesor, MateriaEntity materia, Long idCurso);
}
