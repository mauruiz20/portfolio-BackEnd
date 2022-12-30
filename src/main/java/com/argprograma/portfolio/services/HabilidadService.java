package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Habilidad;
import com.argprograma.portfolio.repositories.HabilidadRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadService {

    HabilidadRepository habilidadRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public HabilidadService (HabilidadRepository habilidadRepository, UsuarioRepository usuarioRepository) {
        this.habilidadRepository = habilidadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Habilidad> buscarHabilidades() {

        return habilidadRepository.findAll();
    }

    public Habilidad dameHabilidad(int idHabilidad) {

        Optional<Habilidad> habilidad = habilidadRepository.findById(idHabilidad);

        if (!habilidad.isPresent()) {
            throw new NotFoundException("Habilidad no encontrada");
        }

        return habilidad.get();
    }

    public void crearHabilidad(Habilidad habilidad) {

        habilidad.setEstadoHab("A");
        validarCampos(habilidad);

        if(!usuarioRepository.existsById(habilidad.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        habilidadRepository.save(habilidad);
    }

    public void modificarHabilidad(Habilidad habilidad) {

        validarCampos(habilidad);

        if(!usuarioRepository.existsById(habilidad.getUsuario().getIdUsuario())) {
            throw new BadRequestException("No existe el usuario");
        }

        habilidadRepository.save(habilidad);
    }

    public void borrarHabilidad(int idHabilidad) {

        try {
            habilidadRepository.deleteById(idHabilidad);
        } catch (Exception e) {
            throw new NotFoundException("Habilidad no encontrada");
        }
    }

    public void darAltaHabilidad(int idHabilidad) {

        Optional<Habilidad> habilidad = habilidadRepository.findById(idHabilidad);

        if(!habilidad.isPresent()) {
            throw new NotFoundException("Habilidad no encontrada");
        }

        habilidad.get().setEstadoHab("A");
        habilidadRepository.save(habilidad.get());
    }

    public void darBajaHabilidad(int idHabilidad) {

        Optional<Habilidad> habilidad = habilidadRepository.findById(idHabilidad);

        if(!habilidad.isPresent()) {
            throw new NotFoundException("Habilidad no encontrada");
        }

        habilidad.get().setEstadoHab("B");
        habilidadRepository.save(habilidad.get());
    }

    private void validarCampos(Habilidad habilidad) {
        if(habilidad.getUsuario() == null ||
                habilidad.getHabilidad() == null || habilidad.getHabilidad().isBlank() ||
                habilidad.getGradoDominio() == null || habilidad.getGradoDominio().isBlank() ||
                habilidad.getEstadoHab() == null || habilidad.getEstadoHab().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
