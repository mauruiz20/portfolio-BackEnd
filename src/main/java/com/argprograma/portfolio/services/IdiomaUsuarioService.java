package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.IdiomaUsuario;
import com.argprograma.portfolio.models.RedUsuario;
import com.argprograma.portfolio.repositories.IdiomaUsuarioRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomaUsuarioService {

    IdiomaUsuarioRepository idiomaUsuarioRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public IdiomaUsuarioService(IdiomaUsuarioRepository idiomaUsuarioRepository, UsuarioRepository usuarioRepository) {
        this.idiomaUsuarioRepository = idiomaUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<IdiomaUsuario> buscarIdiomasUsuario() {

        return idiomaUsuarioRepository.findAll();
    }

    public IdiomaUsuario dameIdiomaUsuario(int idIdiomaUsuario) {

        Optional<IdiomaUsuario> idiomaUsuario = idiomaUsuarioRepository.findById(idIdiomaUsuario);

        if (!idiomaUsuario.isPresent()) {
            throw new NotFoundException("IdiomaUsuario no encontrada");
        }

        return idiomaUsuario.get();
    }

    public void crearIdiomaUsuario(IdiomaUsuario idiomaUsuario) {

        idiomaUsuario.setEstadoIdiomaUsuario("A");
        validarCampos(idiomaUsuario);

        if(!usuarioRepository.existsById(idiomaUsuario.getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        idiomaUsuarioRepository.save(idiomaUsuario);
    }

    public void modificarIdiomaUsuario(IdiomaUsuario idiomaUsuario) {

        validarCampos(idiomaUsuario);

        if(!usuarioRepository.existsById(idiomaUsuario.getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        idiomaUsuarioRepository.save(idiomaUsuario);
    }

    public void borrarIdiomaUsuario(int idIdiomaUsuario) {

        try {
            idiomaUsuarioRepository.deleteById(idIdiomaUsuario);
        } catch (Exception e) {
            throw new NotFoundException("IdiomaUsuario no encontrada");
        }
    }

    public void darAltaIdiomaUsuario(int idIdiomaUsuario) {

        Optional<IdiomaUsuario> idiomaUsuario = idiomaUsuarioRepository.findById(idIdiomaUsuario);

        if(!idiomaUsuario.isPresent()) {
            throw new NotFoundException("IdiomaUsuario no encontrada");
        }

        idiomaUsuario.get().setEstadoIdiomaUsuario("A");
        idiomaUsuarioRepository.save(idiomaUsuario.get());
    }

    public void darBajaIdiomaUsuario(int idIdiomaUsuario) {

        Optional<IdiomaUsuario> idiomaUsuario = idiomaUsuarioRepository.findById(idIdiomaUsuario);

        if(!idiomaUsuario.isPresent()) {
            throw new NotFoundException("IdiomaUsuario no encontrada");
        }

        idiomaUsuario.get().setEstadoIdiomaUsuario("B");
        idiomaUsuarioRepository.save(idiomaUsuario.get());
    }

    public List<IdiomaUsuario> listarIdiomasUsuarioDeUsuario(int idUsuario) {

        return idiomaUsuarioRepository.findByIdUsuario(idUsuario);
    }

    private void validarCampos(IdiomaUsuario idiomaUsuario) {
        if(idiomaUsuario.getIdioma() == null ||
                idiomaUsuario.getNivel() == null || idiomaUsuario.getNivel().isBlank() ||
                idiomaUsuario.getEstadoIdiomaUsuario() == null || idiomaUsuario.getEstadoIdiomaUsuario().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
