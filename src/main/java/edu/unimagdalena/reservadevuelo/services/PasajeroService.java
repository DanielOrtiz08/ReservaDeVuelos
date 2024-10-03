package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.entities.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    Pasajero guardarPasajero(Pasajero pasajero);
    Optional<Pasajero> buscarPasajeroPorId(Long id);
    List<Pasajero> buscarPasajeros();
    List<Pasajero> buscarPasajerosPorNombre(String nombre);
    Optional<Pasajero> buscarPorDocumentoIdentificacion(String documentoIdentificacion);
    Optional<Pasajero> actualizarPasajero(Long id, Pasajero pasajero);
    void eliminarPasajero(Long id);
}
