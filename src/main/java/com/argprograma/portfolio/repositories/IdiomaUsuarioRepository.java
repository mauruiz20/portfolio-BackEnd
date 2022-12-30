package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.IdiomaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdiomaUsuarioRepository extends JpaRepository<IdiomaUsuario, Integer> {

    List<IdiomaUsuario> findByIdUsuario(int idUsuario);
}
