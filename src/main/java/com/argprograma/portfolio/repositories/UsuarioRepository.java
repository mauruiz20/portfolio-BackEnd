package com.argprograma.portfolio.repositories;

import com.argprograma.portfolio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByCuenta(String cuenta);

    boolean existsByCuentaAndIdUsuarioNot(String cuenta, int idUsuario);
}
