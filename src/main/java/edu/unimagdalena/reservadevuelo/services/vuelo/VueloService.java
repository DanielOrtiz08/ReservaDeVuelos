package edu.unimagdalena.reservadevuelo.services.vuelo;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.dto.VueloDto;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    VueloDto guardarVuelo(VueloDto vueloDto);
    Optional<VueloDto> buscarVueloPorId(Long id);
    List<VueloDto> buscarVuelos();
    List<VueloDto> buscarVuelosPorOrigen(String origen);
    List<VueloDto> buscarVuelosPorDestino(String destino);
    List<VueloDto> buscarVuelosPorAeropuerto(Long aeropuertoId);
    List<VueloDto> buscarVuelosPorAerolinea(Long aerolineaId);
    Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloDto);
    VueloDto agregarReservaAVuelo(Long vueloId, ReservaDto reservaDto);
    void eliminarVuelo(Long id);
    boolean existeVueloPorId(Long id);
}

