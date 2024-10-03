package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    Aerolinea guardarAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> buscarAerolineaPorId(Long id);
    List<Aerolinea> buscarAerolineas();
    List<Aerolinea> buscarAerolineasPorNombre(String nombre);
    Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea);
    void eliminarAerolinea(Long id);
}
