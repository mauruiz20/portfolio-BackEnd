package com.argprograma.portfolio.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario", nullable = false)
    private int idUsuario;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Nombres")
    private String nombres;

    @Column(name = "Email")
    private String email;

    @Column(name = "Cuenta")
    private String cuenta;

    @Column(name = "Clave")
    private String clave;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "FotoPerfil")
    private String fotoPerfil;

    @Column(name = "Banner")
    private String banner;

    @Column(name = "Domicilio")
    private String domicilio;

    @Column(name = "Informacion")
    private String informacion;

    @Column(name = "EstadoUsuario")
    private String estadoUsuario;
}
