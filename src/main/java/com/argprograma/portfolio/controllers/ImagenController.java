package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Imagen;
import com.argprograma.portfolio.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/imagenes")
public class ImagenController {

    ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @GetMapping()
    public ResponseEntity<List<Imagen>> buscarImagenes() {

        List<Imagen> imagenes = imagenService.buscarImagenes();
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> dameImagen(@PathVariable("id") int idImagen) {

        Imagen imagen = imagenService.dameImagen(idImagen);
        return new ResponseEntity<>(imagen, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearImagen(@RequestBody Imagen imagen) {

        imagenService.crearImagen(imagen);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarImagen(@PathVariable("id") int idImagen, @RequestBody Imagen imagen) {

        imagen.setIdImagen(idImagen);
        imagenService.modificarImagen(imagen);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarImagen(@PathVariable("id") int idImagen) {

        imagenService.borrarImagen(idImagen);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaImagen(@PathVariable("id") int idImagen) {

        imagenService.darAltaImagen(idImagen);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaImagen(@PathVariable("id") int idImagen) {

        imagenService.darBajaImagen(idImagen);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
