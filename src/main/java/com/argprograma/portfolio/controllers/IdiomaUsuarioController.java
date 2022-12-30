package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.IdiomaUsuario;
import com.argprograma.portfolio.services.IdiomaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/idiomasUsuario")
public class IdiomaUsuarioController {

    IdiomaUsuarioService idiomaUsuarioService;

    @Autowired
    public IdiomaUsuarioController(IdiomaUsuarioService idiomaUsuarioService) {
        this.idiomaUsuarioService = idiomaUsuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<IdiomaUsuario>> buscarIdiomasUsuario() {

        List<IdiomaUsuario> idiomasUsuario = idiomaUsuarioService.buscarIdiomasUsuario();
        return new ResponseEntity<>(idiomasUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdiomaUsuario> dameIdiomaUsuario(@PathVariable("id") int idIdiomaUsuario) {

        IdiomaUsuario idiomaUsuario = idiomaUsuarioService.dameIdiomaUsuario(idIdiomaUsuario);
        return new ResponseEntity<>(idiomaUsuario, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearIdiomaUsuario(@RequestBody IdiomaUsuario idiomaUsuario) {

        idiomaUsuarioService.crearIdiomaUsuario(idiomaUsuario);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarIdiomaUsuario(@PathVariable("id") int idIdiomaUsuario, @RequestBody IdiomaUsuario idiomaUsuario) {

        idiomaUsuario.setIdIdiomaUsuario(idIdiomaUsuario);
        idiomaUsuarioService.modificarIdiomaUsuario(idiomaUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarIdiomaUsuario(@PathVariable("id") int idIdiomaUsuario) {

        idiomaUsuarioService.borrarIdiomaUsuario(idIdiomaUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaIdiomaUsuario(@PathVariable("id") int idIdiomaUsuario) {

        idiomaUsuarioService.darAltaIdiomaUsuario(idIdiomaUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaIdiomaUsuario(@PathVariable("id") int idIdiomaUsuario) {

        idiomaUsuarioService.darBajaIdiomaUsuario(idIdiomaUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
