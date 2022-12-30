package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Educacion;
import com.argprograma.portfolio.repositories.EducacionRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducacionService {

    EducacionRepository educacionRepository;
    UsuarioRepository usuarioRepository;


    @Autowired
    public EducacionService(EducacionRepository educacionRepository, UsuarioRepository usuarioRepository) {
        this.educacionRepository = educacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Educacion> buscarEducaciones() {

        return educacionRepository.findAll();
    }

    public Educacion dameEducacion(int idEducacion) {

        Optional<Educacion> educacion = educacionRepository.findById(idEducacion);

        if (!educacion.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        return educacion.get();
    }

    public void crearEducacion(Educacion educacion) {

        educacion.setEstadoEdu("A");
        validarCampos(educacion);

        if(!usuarioRepository.existsById(educacion.getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        educacionRepository.save(educacion);
    }

    public void modificarEducacion(Educacion educacion) {

        validarCampos(educacion);

        if(!usuarioRepository.existsById(educacion.getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        educacionRepository.save(educacion);
    }

    public void borrarEducacion(int idEducacion) {

        try {
            educacionRepository.deleteById(idEducacion);
        } catch (Exception e) {
            throw new NotFoundException("Educación no encontrada");
        }
    }

    public void darAltaEducacion(int idEducacion) {

        Optional<Educacion> educacion = educacionRepository.findById(idEducacion);

        if(!educacion.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        educacion.get().setEstadoEdu("A");
        educacionRepository.save(educacion.get());
    }

    public void darBajaEducacion(int idEducacion) {

        Optional<Educacion> educacion = educacionRepository.findById(idEducacion);

        if(!educacion.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        educacion.get().setEstadoEdu("B");
        educacionRepository.save(educacion.get());
    }

    public List<Educacion> listarEducacionesUsuario(int idUsuario) {

        return educacionRepository.findByIdUsuario(idUsuario);
    }

    private void validarCampos(Educacion educacion) {
        if(educacion.getEducacion() == null || educacion.getEducacion().isBlank() ||
                educacion.getFechaInicio() == null || educacion.getFechaInicio().isBlank() ||
                educacion.getInstituto() == null || educacion.getInstituto().isBlank() ||
                educacion.getLogo() == null || educacion.getLogo().isBlank() ||
                educacion.getEstadoEdu() == null || educacion.getEstadoEdu().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
