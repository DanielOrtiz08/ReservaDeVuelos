package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import edu.unimagdalena.reservadevuelo.mappers.ReservaMapper;
import edu.unimagdalena.reservadevuelo.mappers.VueloMapper;
import edu.unimagdalena.reservadevuelo.repositories.ClienteRepository;
import edu.unimagdalena.reservadevuelo.repositories.ReservaRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ClienteRepository clienteRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
    }
    @Override
    public ReservaDto guardarReserva(ReservaDto reservaDto) {
        Reserva reserva = ReservaMapper.mapper.reservaDtoToReserva(reservaDto);
        if (reserva.getCliente() != null) {
            clienteRepository.save(reserva.getCliente());
        }
        return ReservaMapper.mapper.reservaToReservaDto(reservaRepository.save(reserva));
    }

    @Override
    public Optional<ReservaDto> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .map(ReservaMapper.mapper::reservaToReservaDto);
    }

    @Override
    public List<ReservaDto> buscarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.mapper.reservaToReservaDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReservasPorCliente(Long clienteId) {
        List<Reserva> reservas = reservaRepository.findByCliente_Id(clienteId);
        return ReservaMapper.mapper.reservaToReservaDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReservasPorVuelo(Long vueloId) {
        List<Reserva> reservas = reservaRepository.findByVuelos_Id(vueloId);
        return ReservaMapper.mapper.reservaToReservaDto(reservas);
    }

    @Override
    public ReservaDto agregarVueloReserva(Long reservaId, VueloDto vueloDto) {
        Vuelo vuelo = VueloMapper.mapper.vueloDtoToVuelo(vueloDto);
        return reservaRepository.findById(reservaId).map(reserva -> {
            reserva.getVuelos().add(vuelo);
            return ReservaMapper.mapper.reservaToReservaDto(reservaRepository.save(reserva));
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {
        return reservaRepository.findById(id).map(oldReserva -> {
            Reserva reserva = ReservaMapper.mapper.reservaDtoToReserva(reservaDto);
            oldReserva.setFechaReserva(reserva.getFechaReserva());
            oldReserva.setNumeroPasajero(reserva.getNumeroPasajero());
            oldReserva.setCliente(reserva.getCliente());
            oldReserva.setPasajeros(reserva.getPasajeros());
            oldReserva.setVuelos(reserva.getVuelos());
            return ReservaMapper.mapper.reservaToReservaDto(reservaRepository.save(oldReserva));
        });
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
