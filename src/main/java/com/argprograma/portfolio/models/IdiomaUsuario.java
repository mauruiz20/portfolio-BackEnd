package com.argprograma.portfolio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "IdiomasUsuario")
public class IdiomaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdIdiomaUsuario")
    private int idIdiomaUsuario;


    @Column(name = "IdUsuario")
    private int idUsuario;

    @ManyToOne
    @JoinColumn(name = "IdIdioma")
    private Idioma idioma;

    @Column(name = "Nivel")
    private String nivel;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "EstadoIdiomaUsuario")
    private String estadoIdiomaUsuario;
}
