package edu.unimagdalena.reservadevuelo.services.pasajero;

import edu.unimagdalena.reservadevuelo.dto.PasajeroDto;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    PasajeroDto guardarPasajero(PasajeroDto pasajeroDto);
    Optional<PasajeroDto> buscarPasajeroPorId(Long id);
    List<PasajeroDto> buscarPasajeros();
    List<PasajeroDto> buscarPasajerosPorNombre(String nombre);
    Optional<PasajeroDto> buscarPorDocumentoIdentificacion(String documentoIdentificacion);
    public Optional<PasajeroDto> actualizarPasajero(Long id, PasajeroDto pasajeroDto);
    void eliminarPasajero(Long id);
    boolean existePasajeroPorId(Long id);
}
