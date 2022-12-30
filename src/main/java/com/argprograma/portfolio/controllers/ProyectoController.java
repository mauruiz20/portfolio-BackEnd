package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Proyecto;
import com.argprograma.portfolio.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/proyectos")
public class ProyectoController {

    ProyectoService proyectoService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping()
    public ResponseEntity<List<Proyecto>> buscarProyectos() {

        List<Proyecto> proyectos = proyectoService.buscarProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> dameProyecto(@PathVariable("id") int idProyecto) {

        Proyecto proyecto = proyectoService.dameProyecto(idProyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearProyecto(@RequestBody Proyecto proyecto) {

        proyectoService.crearProyecto(proyecto);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarProyecto(@PathVariable("id") int idProyecto, @RequestBody Proyecto proyecto) {

        proyecto.setIdProyecto(idProyecto);
        proyectoService.modificarProyecto(proyecto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarProyecto(@PathVariable("id") int idProyecto) {

        proyectoService.borrarProyecto(idProyecto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaProyecto(@PathVariable("id") int idProyecto) {

        proyectoService.darAltaProyecto(idProyecto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaProyecto(@PathVariable("id") int idProyecto) {

        proyectoService.darBajaProyecto(idProyecto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
