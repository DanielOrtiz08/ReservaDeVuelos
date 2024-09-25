package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Reserva;
import edu.unimagdalena.reservadevuelo.Entities.Vuelo;
import edu.unimagdalena.reservadevuelo.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> buscarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> buscarReservasPorCliente(Long clienteId) {
        return reservaRepository.findByCliente_Id(clienteId);
    }

    @Override
    public List<Reserva> buscarReservasPorVuelo(Long vueloId) {
        return reservaRepository.findByVuelos_Id(vueloId);
    }

    @Override
    public Reserva agregarVueloReserva(Long reservaId, Vuelo vuelo) {
        return reservaRepository.findById(reservaId).map(reserva -> {
            reserva.getVuelos().add(vuelo);
            return reservaRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public Optional<Reserva> actualizarReserva(Long id, Reserva reserva) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setFechaReserva(reserva.getFechaReserva());
            oldReserva.setNumeroPasajero(reserva.getNumeroPasajero());
            oldReserva.setCliente(reserva.getCliente());
            oldReserva.setPasajeros(reserva.getPasajeros());
            oldReserva.setVuelos(reserva.getVuelos());
            return reservaRepository.save(oldReserva);
        });
    }
}
