package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Experiencia;
import com.argprograma.portfolio.repositories.ExperienciaRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaService {

    ExperienciaRepository experienciaRepository;
    UsuarioRepository usuarioRepository;


    @Autowired
    public ExperienciaService(ExperienciaRepository experienciaRepository, UsuarioRepository usuarioRepository) {
        this.experienciaRepository = experienciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Experiencia> buscarExperiencias() {

        return experienciaRepository.findAll();
    }

    public Experiencia dameExperiencia(int idExperiencia) {

        Optional<Experiencia> experiencia = experienciaRepository.findById(idExperiencia);

        if (!experiencia.isPresent()) {
            throw new NotFoundException("Experiencia no encontrada");
        }

        return experiencia.get();
    }

    public void crearExperiencia(Experiencia experiencia) {

        experiencia.setEstadoExp("A");
        validarCampos(experiencia);

        if(!usuarioRepository.existsById(experiencia.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        experienciaRepository.save(experiencia);
    }

    public void modificarExperiencia(Experiencia experiencia) {

        validarCampos(experiencia);

        if(!usuarioRepository.existsById(experiencia.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        experienciaRepository.save(experiencia);
    }

    public void borrarExperiencia(int idExperiencia) {

        try {
            experienciaRepository.deleteById(idExperiencia);
        } catch (Exception e) {
            throw new NotFoundException("Experiencia no encontrada");
        }
    }

    public void darAltaExperiencia(int idExperiencia) {

        Optional<Experiencia> experiencia = experienciaRepository.findById(idExperiencia);

        if(!experiencia.isPresent()) {
            throw new NotFoundException("Experiencia no encontrada");
        }

        experiencia.get().setEstadoExp("A");
        experienciaRepository.save(experiencia.get());
    }

    public void darBajaExperiencia(int idExperiencia) {

        Optional<Experiencia> experiencia = experienciaRepository.findById(idExperiencia);

        if(!experiencia.isPresent()) {
            throw new NotFoundException("Experiencia no encontrada");
        }

        experiencia.get().setEstadoExp("B");
        experienciaRepository.save(experiencia.get());
    }

    private void validarCampos(Experiencia experiencia) {
        if(experiencia.getUsuario() == null ||
                experiencia.getExperiencia() == null || experiencia.getExperiencia().isBlank() ||
                experiencia.getLugar() == null || experiencia.getLugar().isBlank() ||
                experiencia.getTituloPuesto() == null || experiencia.getTituloPuesto().isBlank() ||
                experiencia.getLogoEmpresa() == null || experiencia.getLogoEmpresa().isBlank() ||
                experiencia.getEstadoExp() == null || experiencia.getEstadoExp().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
