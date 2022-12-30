package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.RedUsuario;
import com.argprograma.portfolio.services.RedUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/redesUsuario")
public class RedUsuarioController {

    RedUsuarioService redUsuarioService;

    @Autowired
    public RedUsuarioController(RedUsuarioService redUsuarioService) {
        this.redUsuarioService = redUsuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<RedUsuario>> buscarRedesUsuario() {

        List<RedUsuario> redesUsuario = redUsuarioService.buscarRedesUsuario();
        return new ResponseEntity<>(redesUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedUsuario> dameRedUsuario(@PathVariable("id") int idRedUsuario) {

        RedUsuario redUsuario = redUsuarioService.dameRedUsuario(idRedUsuario);
        return new ResponseEntity<>(redUsuario, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearRedUsuario(@RequestBody RedUsuario redUsuario) {

        redUsuarioService.crearRedUsuario(redUsuario);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarRedUsuario(@PathVariable("id") int idRedUsuario, @RequestBody RedUsuario redUsuario) {

        redUsuario.setIdRedUsuario(idRedUsuario);
        redUsuarioService.modificarRedUsuario(redUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarRedUsuario(@PathVariable("id") int idRedUsuario) {

        redUsuarioService.borrarRedUsuario(idRedUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaRedUsuario(@PathVariable("id") int idRedUsuario) {

        redUsuarioService.darAltaRedUsuario(idRedUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaRedUsuario(@PathVariable("id") int idRedUsuario) {

        redUsuarioService.darBajaRedUsuario(idRedUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
