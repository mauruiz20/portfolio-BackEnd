package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
}
