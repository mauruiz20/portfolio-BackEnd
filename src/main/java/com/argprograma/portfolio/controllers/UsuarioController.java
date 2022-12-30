package com.argprograma.portfolio.controllers;

import com.argprograma.portfolio.models.Usuario;
import com.argprograma.portfolio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> buscaUsuarios() {

        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> dameUsuario(@PathVariable("id") int idUsuario) {

        Usuario usuario = usuarioService.dameUsuario(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {

        usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> modificarUsuario(@PathVariable("id") int idUsuario, @RequestBody Usuario usuario) {

        usuario.setIdUsuario(idUsuario);
        usuarioService.modificarUsuario(usuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable("id") int idUsuario) {

        usuarioService.borrarUsuario(idUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/alta")
    public ResponseEntity<String> darAltaUsuario(@PathVariable("id") int idUsuario) {

        usuarioService.darAltaUsuario(idUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping("{id}/baja")
    public ResponseEntity<String> darBajaUsuario(@PathVariable("id") int idUsuario) {

        usuarioService.darBajaUsuario(idUsuario);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
