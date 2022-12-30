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
@Table(name = "Imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdImagen")
    private int idImagen;

    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyecto proyecto;

    @Column(name = "IdUsuario")
    private int idUsuario;

    @Column(name = "Link")
    private String link;

    @Column(name = "EstadoImg")
    private String estadoImg;
}
