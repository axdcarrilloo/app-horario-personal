package com.casa.repositories;

import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.HorasDiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasDiaRepository extends JpaRepository<HorasDiaEntity, Long> {

    List<HorasDiaEntity> findByHorario(HorarioEntity horario);

    List<HorasDiaEntity> findByHorarioAndDia(HorarioEntity horario, DiaEntity dia);

    List<HorasDiaEntity> findByDia(DiaEntity dia);

}
