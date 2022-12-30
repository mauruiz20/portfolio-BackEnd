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
@Table(name = "Experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExperiencia")
    private int idExperiencia;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @Column(name = "Experiencia")
    private String experiencia;

    @Column(name = "Lugar")
    private String lugar;

    @Column(name = "TituloPuesto")
    private String tituloPuesto;

    @Column(name = "LogoEmpresa")
    private String logoEmpresa;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "EstadoExp")
    private String estadoExp;
}
