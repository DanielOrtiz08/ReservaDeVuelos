package edu.unimagdalena.reservadevuelo.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "aerolineas")
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "codigo_aerolinea", unique = true,  nullable = false)
    private long codigoAerolinea;

    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;

    @OneToMany(mappedBy = "aerolinea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vuelo> Vuelos;
}
