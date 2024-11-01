package edu.unimagdalena.reservadevuelo.services.reserva;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import edu.unimagdalena.reservadevuelo.mappers.ReservaMapper;
import edu.unimagdalena.reservadevuelo.mappers.VueloMapper;
import edu.unimagdalena.reservadevuelo.repositories.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final VueloMapper vueloMapper;

    @Override
    public ReservaDto guardarReserva(ReservaDto reservaDto) {
        Reserva reserva = reservaMapper.reservaDtoToReserva(reservaDto);
        return reservaMapper.reservaToReservaDto(reservaRepository.save(reserva));
    }

    @Override
    public Optional<ReservaDto> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .map(reservaMapper::reservaToReservaDto);
    }

    @Override
    public List<ReservaDto> buscarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservaMapper.reservaToReservaDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReservasPorCliente(Long clienteId) {
        List<Reserva> reservas = reservaRepository.findByCliente_Id(clienteId);
        return reservaMapper.reservaToReservaDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReservasPorVuelo(Long vueloId) {
        List<Reserva> reservas = reservaRepository.findByVuelos_Id(vueloId);
        return reservaMapper.reservaToReservaDto(reservas);
    }

    @Override
    public ReservaDto agregarVueloAReserva(Long reservaId, VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.vueloDtoToVuelo(vueloDto);

        return reservaRepository.findById(reservaId)
                .map(reserva -> {
                    reserva.getVuelos().add(vuelo);
                    return reservaMapper.reservaToReservaDto(reservaRepository.save(reserva));
                })
                .orElse(null);
    }


    @Override
    public Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {
        return reservaRepository.findById(id).map(oldReserva -> {
            Reserva reserva = reservaMapper.reservaDtoToReserva(reservaDto);
            oldReserva.setFechaReserva(reserva.getFechaReserva());
            oldReserva.setNumeroPasajero(reserva.getNumeroPasajero());
            oldReserva.setCliente(reserva.getCliente());
            oldReserva.setPasajeros(reserva.getPasajeros());
            oldReserva.setVuelos(reserva.getVuelos());
            return reservaMapper.reservaToReservaDto(reservaRepository.save(oldReserva));
        });
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean existeReservaPorId(Long id) {
        return reservaRepository.existsById(id);
    }
}
