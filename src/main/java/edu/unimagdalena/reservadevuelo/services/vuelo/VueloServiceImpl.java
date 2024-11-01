package edu.unimagdalena.reservadevuelo.services.vuelo;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import edu.unimagdalena.reservadevuelo.mappers.ReservaMapper;
import edu.unimagdalena.reservadevuelo.mappers.VueloMapper;
import edu.unimagdalena.reservadevuelo.repositories.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMaper;
    private final ReservaMapper reservaMapper;

    @Override
    public VueloDto guardarVuelo(VueloDto vueloDto) {
        Vuelo vuelo = vueloMaper.vueloDtoToVuelo(vueloDto);
        Vuelo savedVuelo = vueloRepository.save(vuelo);
        return vueloMaper.vueloToVueloDto(savedVuelo);
    }

    @Override
    public Optional<VueloDto> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id)
                .map(vueloMaper::vueloToVueloDto);
    }

    @Override
    public List<VueloDto> buscarVuelos() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        return vueloMaper.vueloToVueloDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVuelosPorOrigen(String origen) {
        List<Vuelo> vuelos = vueloRepository.findByOrigen(origen);
        return vueloMaper.vueloToVueloDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVuelosPorDestino(String destino) {
        List<Vuelo> vuelos = vueloRepository.findByDestino(destino);
        return vueloMaper.vueloToVueloDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVuelosPorAeropuerto(Long aeropuertoId) {
        List<Vuelo> vuelos = vueloRepository.findByAeropuerto_Id(aeropuertoId);
        return vueloMaper.vueloToVueloDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVuelosPorAerolinea(Long aerolineaId) {
        List<Vuelo> vuelos = vueloRepository.findByAerolinea_Id(aerolineaId);
        return vueloMaper.vueloToVueloDto(vuelos);
    }

    @Override
    public Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloDto) {
        return vueloRepository.findById(id).map(oldVuelo -> {
            Vuelo vueloToUpdate = vueloMaper.vueloDtoToVuelo(vueloDto);
            oldVuelo.setOrigen(vueloToUpdate.getOrigen());
            oldVuelo.setDestino(vueloToUpdate.getDestino());
            oldVuelo.setFechaSalida(vueloToUpdate.getFechaSalida());
            oldVuelo.setHoraSalida(vueloToUpdate.getHoraSalida());
            oldVuelo.setDuracion(vueloToUpdate.getDuracion());
            oldVuelo.setCapacidad(vueloToUpdate.getCapacidad());
            oldVuelo.setAeropuerto(vueloToUpdate.getAeropuerto());
            oldVuelo.setAerolinea(vueloToUpdate.getAerolinea());
            return vueloMaper.vueloToVueloDto(vueloRepository.save(oldVuelo));
        });
    }

    @Override
    public VueloDto agregarReservaAVuelo(Long vueloId, ReservaDto reservaDto) {
        Reserva reserva = reservaMapper.reservaDtoToReserva(reservaDto);
        return vueloRepository.findById(vueloId).map(vuelo -> {
            vuelo.getReservas().add(reserva);
            Vuelo savedVuelo = vueloRepository.save(vuelo);
            return vueloMaper.vueloToVueloDto(savedVuelo);
        }).orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
    }

    @Override
    public void eliminarVuelo(Long id) {
        vueloRepository.deleteById(id);
    }

    @Override
    public boolean existeVueloPorId(Long id) {
        return vueloRepository.existsById(id);
    }
}
