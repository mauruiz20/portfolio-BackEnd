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
@Table(name = "Habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHabilidad")
    private int idHabilidad;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @Column(name = "Habilidad")
    private String habilidad;

    @Column(name = "GradoDominio")
    private String gradoDominio;

    @Column(name = "EstadoHab")
    private String estadoHab;
}
