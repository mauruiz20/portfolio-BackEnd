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
@Table(name = "Educaciones")
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEducacion")
    private int idEducacion;

    @Column(name = "IdUsuario")
    private int idUsuario;

    @Column(name = "Educacion")
    private String educacion;

    @Column(name = "FechaInicio")
    private String fechaInicio;

    @Column(name = "FechaFin")
    private String fechaFin;

    @Column(name = "Instituto")
    private String instituto;

    @Column(name = "Logo")
    private String logo;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "EstadoEdu")
    private String estadoEdu;
}
