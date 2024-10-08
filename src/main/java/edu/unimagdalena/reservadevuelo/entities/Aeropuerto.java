package edu.unimagdalena.reservadevuelo.entities;

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
@Table(name = "aeropuertos")
public class Aeropuerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String pais;

    @Column(name = "codigo_IATA", nullable = false, unique = true)
    private String codigoIATA;

    @OneToMany(mappedBy = "aeropuerto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vuelo> vuelos;
}
