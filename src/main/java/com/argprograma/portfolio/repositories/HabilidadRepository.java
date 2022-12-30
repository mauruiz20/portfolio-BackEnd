package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {

    List<Habilidad> findByIdUsuario(int idUsuario);
}
