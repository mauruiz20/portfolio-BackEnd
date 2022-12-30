package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Educacion;
import com.argprograma.portfolio.services.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/educaciones")
public class EducacionController {

    EducacionService educacionService;

    @Autowired
    public EducacionController(EducacionService educacionService) {
        this.educacionService = educacionService;
    }

    @GetMapping()
    public ResponseEntity<List<Educacion>> buscarEducaciones() {

        List<Educacion> educaciones = educacionService.buscarEducaciones();
        return new ResponseEntity<>(educaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Educacion> dameEducacion(@PathVariable("id") int idEducacion) {

        Educacion educacion = educacionService.dameEducacion(idEducacion);
        return new ResponseEntity<>(educacion, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearEducacion(@RequestBody Educacion educacion) {

        educacionService.crearEducacion(educacion);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarEducacion(@PathVariable("id") int idEducacion, @RequestBody Educacion educacion) {

        educacion.setIdEducacion(idEducacion);
        educacionService.modificarEducacion(educacion);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarEducacion(@PathVariable("id") int idEducacion) {

        educacionService.borrarEducacion(idEducacion);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaEducacion(@PathVariable("id") int idEducacion) {

        educacionService.darAltaEducacion(idEducacion);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaEducacion(@PathVariable("id") int idEducacion) {

        educacionService.darBajaEducacion(idEducacion);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
