package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByIdUsuario(int idUsuario);
}
