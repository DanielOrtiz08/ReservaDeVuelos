package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Reserva;
import edu.unimagdalena.reservadevuelo.Entities.Vuelo;
import edu.unimagdalena.reservadevuelo.repositories.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    private VueloRepository vueloRepository;


    @Override
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Optional<Vuelo> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public List<Vuelo> buscarVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public List<Vuelo> buscarVuelosPorOrigen(String origen) {
        return vueloRepository.findByOrigen(origen);
    }

    @Override
    public List<Vuelo> buscarVuelosPorDestino(String destino) {
        return vueloRepository.findByDestino(destino);
    }

    @Override
    public List<Vuelo> buscarVuelosPorAeropuerto(Long aeropuertoId) {
        return vueloRepository.findByAeropuerto_Id(aeropuertoId);
    }

    @Override
    public List<Vuelo> buscarVuelosPorAerolinea(Long aerolineaId) {
        return vueloRepository.findByAerolinea_Id(aerolineaId);
    }

    @Override
    public Optional<Vuelo> actualizarVuelo(Long id, Vuelo vuelo) {
        return vueloRepository.findById(id).map(oldVuelo -> {
            oldVuelo.setOrigen(vuelo.getOrigen());
            oldVuelo.setDestino(vuelo.getDestino());
            oldVuelo.setFechaSalida(vuelo.getFechaSalida());
            oldVuelo.setHoraSalida(vuelo.getHoraSalida());
            oldVuelo.setDuracion(vuelo.getDuracion());
            oldVuelo.setCapacidad(vuelo.getCapacidad());
            oldVuelo.setAeropuerto(vuelo.getAeropuerto());
            oldVuelo.setAerolinea(vuelo.getAerolinea());
            return vueloRepository.save(oldVuelo);
        });
    }

    @Override
    public Vuelo agregarReservaVuelo(Long vueloId, Reserva reserva) {
        return vueloRepository.findById(vueloId).map(vuelo -> {
            vuelo.getReservas().add(reserva);
            return vueloRepository.save(vuelo);
        }).orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
    }

    @Override
    public void eliminarVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}
