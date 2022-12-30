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
@Table(name = "Proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProyecto")
    private int idProyecto;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @Column(name = "Proyecto")
    private String proyecto;

    @Column(name = "FechaInicio")
    private String fechaInicio;

    @Column(name = "FechaFin")
    private String fechaFin;

    @Column(name = "Link")
    private String link;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "EstadoProyecto")
    private String estadoProyecto;
}
