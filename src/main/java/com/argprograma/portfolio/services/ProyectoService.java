package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Proyecto;
import com.argprograma.portfolio.repositories.ProyectoRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    ProyectoRepository proyectoRepository;
    UsuarioRepository usuarioRepository;


    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository, UsuarioRepository usuarioRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Proyecto> buscarProyectos() {

        return proyectoRepository.findAll();
    }

    public Proyecto dameProyecto(int idProyecto) {

        Optional<Proyecto> proyecto = proyectoRepository.findById(idProyecto);

        if (!proyecto.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        return proyecto.get();
    }

    public void crearProyecto(Proyecto proyecto) {

        proyecto.setEstadoProyecto("A");
        validarCampos(proyecto);

        if(!usuarioRepository.existsById(proyecto.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        proyectoRepository.save(proyecto);
    }

    public void modificarProyecto(Proyecto proyecto) {

        validarCampos(proyecto);

        if(!usuarioRepository.existsById(proyecto.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        proyectoRepository.save(proyecto);
    }

    public void borrarProyecto(int idProyecto) {

        try {
            proyectoRepository.deleteById(idProyecto);
        } catch (Exception e) {
            throw new NotFoundException("Educación no encontrada");
        }
    }

    public void darAltaProyecto(int idProyecto) {

        Optional<Proyecto> proyecto = proyectoRepository.findById(idProyecto);

        if(!proyecto.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        proyecto.get().setEstadoProyecto("A");
        proyectoRepository.save(proyecto.get());
    }

    public void darBajaProyecto(int idProyecto) {

        Optional<Proyecto> proyecto = proyectoRepository.findById(idProyecto);

        if(!proyecto.isPresent()) {
            throw new NotFoundException("Educación no encontrada");
        }

        proyecto.get().setEstadoProyecto("B");
        proyectoRepository.save(proyecto.get());
    }

    private void validarCampos(Proyecto proyecto) {
        if(proyecto.getUsuario() == null ||
                proyecto.getProyecto() == null || proyecto.getProyecto().isBlank() ||
                proyecto.getFechaInicio() == null || proyecto.getFechaInicio().isBlank() ||
                proyecto.getEstadoProyecto() == null || proyecto.getEstadoProyecto().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
