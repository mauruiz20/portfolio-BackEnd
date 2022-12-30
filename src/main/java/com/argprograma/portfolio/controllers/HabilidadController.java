package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Habilidad;
import com.argprograma.portfolio.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/habilidades")
public class HabilidadController {

    HabilidadService habilidadService;

    @Autowired
    public HabilidadController(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    @GetMapping()
    public ResponseEntity<List<Habilidad>> buscarHabilidades() {

        List<Habilidad> habilidades = habilidadService.buscarHabilidades();
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> dameHabilidad(@PathVariable("id") int idHabilidad) {

        Habilidad habilidad = habilidadService.dameHabilidad(idHabilidad);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearHabilidad(@RequestBody Habilidad habilidad) {

        habilidadService.crearHabilidad(habilidad);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarHabilidad(@PathVariable("id") int idHabilidad, @RequestBody Habilidad habilidad) {

        habilidad.setIdHabilidad(idHabilidad);
        habilidadService.modificarHabilidad(habilidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarHabilidad(@PathVariable("id") int idHabilidad) {

        habilidadService.borrarHabilidad(idHabilidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaHabilidad(@PathVariable("id") int idHabilidad) {

        habilidadService.darAltaHabilidad(idHabilidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaHabilidad(@PathVariable("id") int idHabilidad) {

        habilidadService.darBajaHabilidad(idHabilidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
