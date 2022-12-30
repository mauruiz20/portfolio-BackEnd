package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Usuario;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarUsuarios() {

        return usuarioRepository.findAll();
    }

    public Usuario dameUsuario(int idUsuario) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return usuario.get();
    }

    public void crearUsuario(Usuario usuario) {

        usuario.setEstadoUsuario("A");
        validarCampos(usuario);

        if(usuarioRepository.existsByCuenta(usuario.getCuenta())) {
            throw new BadRequestException("Cuenta ya existente");
        }

        usuarioRepository.save(usuario);
    }

    public void modificarUsuario(Usuario usuario) {

        validarCampos(usuario);

        if(usuarioRepository.existsByCuentaAndIdUsuarioNot(usuario.getCuenta(), usuario.getIdUsuario())) {
            throw new BadRequestException("Cuenta ya existente");
        }

        usuarioRepository.save(usuario);
    }

    public void borrarUsuario(int idUsuario) {

        try {
            usuarioRepository.deleteById(idUsuario);
        } catch (Exception e) {
            throw new NotFoundException("Usuario no encontrado");
        }
    }

    public void darAltaUsuario(int idUsuario) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if(!usuario.isPresent()) {
            throw new NotFoundException("Usuario no encontrado");
        }

        usuario.get().setEstadoUsuario("A");
        usuarioRepository.save(usuario.get());
    }

    public void darBajaUsuario(int idUsuario) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if(!usuario.isPresent()) {
            throw new NotFoundException("Usuario no encontrado");
        }

        usuario.get().setEstadoUsuario("B");
        usuarioRepository.save(usuario.get());
    }

    private void validarCampos(Usuario usuario) {
        if(usuario.getApellidos() == null || usuario.getApellidos().isBlank() ||
                usuario.getNombres() == null || usuario.getNombres().isBlank() ||
                usuario.getEmail() == null || usuario.getEmail().isBlank() ||
                usuario.getCuenta() == null || usuario.getCuenta().isBlank() ||
                usuario.getClave() == null || usuario.getClave().isBlank() ||
                usuario.getTitulo() == null || usuario.getTitulo().isBlank() ||
                usuario.getFotoPerfil() == null || usuario.getFotoPerfil().isBlank() ||
                usuario.getBanner() == null || usuario.getBanner().isBlank() ||
                usuario.getEstadoUsuario() == null || usuario.getEstadoUsuario().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
