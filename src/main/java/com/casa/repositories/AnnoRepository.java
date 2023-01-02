package com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.AnnoEntity;

@Repository
public interface AnnoRepository extends JpaRepository<AnnoEntity, Long>{

}
