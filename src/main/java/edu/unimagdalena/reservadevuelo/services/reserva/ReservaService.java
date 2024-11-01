package edu.unimagdalena.reservadevuelo.services.reserva;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.dto.VueloDto;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    ReservaDto guardarReserva(ReservaDto reservaDto);
    Optional<ReservaDto> buscarReservaPorId(Long id);
    List<ReservaDto> buscarReservas();
    List<ReservaDto> buscarReservasPorCliente(Long clienteId);
    List<ReservaDto> buscarReservasPorVuelo(Long vueloId);
    ReservaDto agregarVueloAReserva(Long reservaId, VueloDto vueloDto);
    Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto);
    void eliminarReserva(Long id);
    boolean existeReservaPorId(Long id);
}
