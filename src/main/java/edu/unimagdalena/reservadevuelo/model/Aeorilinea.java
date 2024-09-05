package edu.unimagdalena.reservadevuelo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "aerolineas")
public class Aeorilinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "codigo_aerolinea", unique = true,  nullable = false)
    private long codigoAerolinea;

    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;
}
