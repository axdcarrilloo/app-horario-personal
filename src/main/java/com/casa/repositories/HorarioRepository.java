package com.casa.repositories;

import com.casa.domain.entities.HorarioEntity;
import com.casa.utils.ConstantesSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Long> {

    @Transactional
    @Query(value = ConstantesSQL.CONSULTARPOR_IDDIA, nativeQuery = true)
    List<HorarioEntity> findByIdDia(@Param("idDia")Long idDia);

}
