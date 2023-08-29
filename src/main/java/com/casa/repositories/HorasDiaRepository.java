package com.casa.repositories;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.HorasDiaEntity;
import com.casa.utils.ConstantesSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HorasDiaRepository extends JpaRepository<HorasDiaEntity, Long> {

    List<HorasDiaEntity> findByHorario(HorarioEntity horario);

    List<HorasDiaEntity> findByHorarioAndDia(HorarioEntity horario, DiaEntity dia);

    List<HorasDiaEntity> findByDia(DiaEntity dia);

}
