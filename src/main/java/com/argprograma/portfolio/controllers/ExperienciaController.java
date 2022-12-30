package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Experiencia;
import com.argprograma.portfolio.services.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/experiencias")
public class ExperienciaController {

    ExperienciaService experienciaService;

    @Autowired
    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping()
    public ResponseEntity<List<Experiencia>> buscarExperiencias() {

        List<Experiencia> experiencias = experienciaService.buscarExperiencias();
        return new ResponseEntity<>(experiencias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> dameExperiencia(@PathVariable("id") int idExperiencia) {

        Experiencia experiencia = experienciaService.dameExperiencia(idExperiencia);
        return new ResponseEntity<>(experiencia, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearExperiencia(@RequestBody Experiencia experiencia) {

        experienciaService.crearExperiencia(experiencia);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarExperiencia(@PathVariable("id") int idExperiencia, @RequestBody Experiencia experiencia) {

        experiencia.setIdExperiencia(idExperiencia);
        experienciaService.modificarExperiencia(experiencia);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarExperiencia(@PathVariable("id") int idExperiencia) {

        experienciaService.borrarExperiencia(idExperiencia);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaExperiencia(@PathVariable("id") int idExperiencia) {

        experienciaService.darAltaExperiencia(idExperiencia);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaExperiencia(@PathVariable("id") int idExperiencia) {

        experienciaService.darBajaExperiencia(idExperiencia);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
