package com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.MesEntity;

@Repository
public interface MesRepository extends JpaRepository<MesEntity, Long> {

}
