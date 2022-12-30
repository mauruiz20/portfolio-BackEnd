package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {

    List<Experiencia> findByIdUsuario(int idUsuario);
}
