package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.RedUsuario;
import com.argprograma.portfolio.repositories.RedUsuarioRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedUsuarioService {

    RedUsuarioRepository redUsuarioRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public RedUsuarioService(RedUsuarioRepository redUsuarioRepository, UsuarioRepository usuarioRepository) {
        this.redUsuarioRepository = redUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RedUsuario> buscarRedesUsuario() {

        return redUsuarioRepository.findAll();
    }

    public RedUsuario dameRedUsuario(int idRedUsuario) {

        Optional<RedUsuario> redUsuario = redUsuarioRepository.findById(idRedUsuario);

        if (!redUsuario.isPresent()) {
            throw new NotFoundException("Red del usuario no encontrada");
        }

        return redUsuario.get();
    }

    public void crearRedUsuario(RedUsuario redUsuario) {

        redUsuario.setEstadoRedUsuario("A");
        validarCampos(redUsuario);

        if(!usuarioRepository.existsById(redUsuario.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        redUsuarioRepository.save(redUsuario);
    }

    public void modificarRedUsuario(RedUsuario redUsuario) {

        validarCampos(redUsuario);

        if(!usuarioRepository.existsById(redUsuario.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        redUsuarioRepository.save(redUsuario);
    }

    public void borrarRedUsuario(int idRedUsuario) {

        try {
            redUsuarioRepository.deleteById(idRedUsuario);
        } catch (Exception e) {
            throw new NotFoundException("Red del usuario no encontrada");
        }
    }

    public void darAltaRedUsuario(int idRedUsuario) {

        Optional<RedUsuario> redUsuario = redUsuarioRepository.findById(idRedUsuario);

        if(!redUsuario.isPresent()) {
            throw new NotFoundException("Red del usuario no encontrada");
        }

        redUsuario.get().setEstadoRedUsuario("A");
        redUsuarioRepository.save(redUsuario.get());
    }

    public void darBajaRedUsuario(int idRedUsuario) {

        Optional<RedUsuario> redUsuario = redUsuarioRepository.findById(idRedUsuario);

        if(!redUsuario.isPresent()) {
            throw new NotFoundException("Red del usuario no encontrada");
        }

        redUsuario.get().setEstadoRedUsuario("B");
        redUsuarioRepository.save(redUsuario.get());
    }

    private void validarCampos(RedUsuario redUsuario) {
        if(redUsuario.getUsuario() == null ||
                redUsuario.getRed() == null ||
                redUsuario.getEstadoRedUsuario() == null || redUsuario.getEstadoRedUsuario().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
