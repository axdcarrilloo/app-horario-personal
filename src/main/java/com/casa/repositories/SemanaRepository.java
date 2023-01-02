package com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.SemanaEntity;

@Repository
public interface SemanaRepository extends JpaRepository<SemanaEntity, Long> {

}
