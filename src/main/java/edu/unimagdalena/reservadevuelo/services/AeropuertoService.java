package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {
    Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto);
    Optional<Aeropuerto> buscarAeropuertoPorId(Long id);
    List<Aeropuerto> buscarAeropuertos();
    List<Aeropuerto> buscarAeropuertosPorNombre(String nombre);
    List<Aeropuerto> buscarAeropuertosPorCodigoIATA(String codigoIATA);
    Optional<Aeropuerto> actualizarAeropuerto(Long id, Aeropuerto aeropuerto);
    void eliminarAeropuerto(Long id);
}

