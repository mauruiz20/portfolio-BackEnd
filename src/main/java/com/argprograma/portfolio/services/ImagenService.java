package com.argprograma.portfolio.services;

import com.argprograma.portfolio.exceptions.BadRequestException;
import com.argprograma.portfolio.exceptions.NotFoundException;
import com.argprograma.portfolio.models.Imagen;
import com.argprograma.portfolio.repositories.ImagenRepository;
import com.argprograma.portfolio.repositories.ProyectoRepository;
import com.argprograma.portfolio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    ImagenRepository imagenRepository;
    ProyectoRepository proyectoRepository;
    UsuarioRepository usuarioRepository;


    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ProyectoRepository proyectoRepository, UsuarioRepository usuarioRepository) {
        this.imagenRepository = imagenRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Imagen> buscarImagenes() {

        return imagenRepository.findAll();
    }

    public Imagen dameImagen(int idImagen) {

        Optional<Imagen> imagen = imagenRepository.findById(idImagen);

        if (!imagen.isPresent()) {
            throw new NotFoundException("Imagen no encontrada");
        }

        return imagen.get();
    }

    public void crearImagen(Imagen imagen) {

        imagen.setEstadoImg("A");
        validarCampos(imagen);

        if(!proyectoRepository.existsById(imagen.getProyecto().getIdProyecto())) {
            throw new BadRequestException("No existe el proyecto");
        }

        imagenRepository.save(imagen);
    }

    public void modificarImagen(Imagen imagen) {

        validarCampos(imagen);

        if(!proyectoRepository.existsById(imagen.getProyecto().getIdProyecto())) {
            throw new BadRequestException("No existe el proyecto");
        }

        imagenRepository.save(imagen);
    }

    public void borrarImagen(int idImagen) {

        try {
            imagenRepository.deleteById(idImagen);
        } catch (Exception e) {
            throw new NotFoundException("Imagen no encontrada");
        }
    }

    public void darAltaImagen(int idImagen) {

        Optional<Imagen> imagen = imagenRepository.findById(idImagen);

        if(!imagen.isPresent()) {
            throw new NotFoundException("Imagen no encontrada");
        }

        imagen.get().setEstadoImg("A");
        imagenRepository.save(imagen.get());
    }

    public void darBajaImagen(int idImagen) {

        Optional<Imagen> imagen = imagenRepository.findById(idImagen);

        if(!imagen.isPresent()) {
            throw new NotFoundException("Imagen no encontrada");
        }

        imagen.get().setEstadoImg("B");
        imagenRepository.save(imagen.get());
    }

    private void validarCampos(Imagen imagen) {
        if(imagen.getProyecto() == null ||
                imagen.getLink() == null || imagen.getLink().isBlank() ||
                imagen.getEstadoImg() == null || imagen.getEstadoImg().isBlank()
        ) {
            throw new BadRequestException("Parámetros erróneos");
        }
    }
}
