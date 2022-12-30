package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.RedUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedUsuarioRepository extends JpaRepository<RedUsuario, Integer> {
}
