package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.entities.Reserva;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    Vuelo guardarVuelo(Vuelo vuelo);
    Optional<Vuelo> buscarVueloPorId(Long id);
    List<Vuelo> buscarVuelos();
    List<Vuelo> buscarVuelosPorOrigen(String origen);
    List<Vuelo> buscarVuelosPorDestino(String destino);
    List<Vuelo> buscarVuelosPorAeropuerto(Long aeropuertoId);
    List<Vuelo> buscarVuelosPorAerolinea(Long aerolineaId);
    Optional<Vuelo> actualizarVuelo(Long id, Vuelo vuelo);
    Vuelo agregarReservaVuelo(Long vueloId, Reserva reserva);
    void eliminarVuelo(Long id);
}

