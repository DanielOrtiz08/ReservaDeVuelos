package edu.unimagdalena.reservadevuelo.services.aerolinea;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    AerolineaDto guardarAerolinea(AerolineaDto aerolineaDto);
    Optional<AerolineaDto> buscarAerolineaPorId(Long id);
    List<AerolineaDto> buscarAerolineas();
    List<AerolineaDto> buscarAerolineasPorNombre(String nombre);
    Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolineaDto);
    void eliminarAerolinea(Long id);
}
