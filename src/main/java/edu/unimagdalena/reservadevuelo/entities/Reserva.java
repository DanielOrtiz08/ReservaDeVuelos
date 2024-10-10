package edu.unimagdalena.reservadevuelo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "numero_pasajero", nullable = false)
    private int numeroPasajero;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pasajero> pasajeros;

    @ManyToMany
    @JoinTable(
            name = "rutas",
            joinColumns = @JoinColumn(name = "reserva_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "id")
    )
    private Set<Vuelo> vuelos;
}
