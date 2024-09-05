package edu.unimagdalena.reservadevuelo.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDateTime fechaSalida;

    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @Column(nullable = false)
    private LocalTime duracion;

    @Column(nullable = false)
    private int capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aeropuerto_id")
    private Aeropuerto aeropuerto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

    @ManyToMany(mappedBy = "vuelos")
    private Set<Reserva> reservas;
}
