package edu.unimagdalena.reservadevuelo.services.aeropuerto;

import edu.unimagdalena.reservadevuelo.dto.AeropuertoDto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {
    AeropuertoDto guardarAeropuerto(AeropuertoDto aeropuertoDto);
    Optional<AeropuertoDto> buscarAeropuertoPorId(Long id);
    List<AeropuertoDto> buscarAeropuertos();
    List<AeropuertoDto> buscarAeropuertosPorNombre(String nombre);
    Optional<AeropuertoDto> buscarAeropuertoPorCodigoIATA(String codigoIATA);
    Optional<AeropuertoDto> actualizarAeropuerto(Long id, AeropuertoDto AeropuertoDto);
    void eliminarAeropuerto(Long id);
    boolean existeAeropuertoPorId(Long id);
}

