package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Reserva;
import edu.unimagdalena.reservadevuelo.Entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva guardarReserva(Reserva reserva);
    Optional<Reserva> buscarReservaPorId(Long id);
    List<Reserva> buscarReservas();
    List<Reserva> buscarReservasPorCliente(Long clienteId);
    List<Reserva> buscarReservasPorVuelo(Long vueloId);
    Reserva agregarVueloReserva(Long reservaId, Vuelo vuelo);
    Optional<Reserva> actualizarReserva(Long id, Reserva reserva);
    void eliminarReserva(Long id);
}
