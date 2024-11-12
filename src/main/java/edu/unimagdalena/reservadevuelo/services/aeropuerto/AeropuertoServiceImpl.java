package edu.unimagdalena.reservadevuelo.services.aeropuerto;

import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import edu.unimagdalena.reservadevuelo.repositories.AeropuertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {

    private AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }

    @Override
    public Optional<Aeropuerto> buscarAeropuertoPorId(Long id) {
        return aeropuertoRepository.findById(id);
    }

    @Override
    public List<Aeropuerto> buscarAeropuertos() {
        return aeropuertoRepository.findAll();
    }

    @Override
    public List<Aeropuerto> buscarAeropuertosPorNombre(String nombre) {
        return aeropuertoRepository.findByNombre(nombre);
    }

    @Override
    public List<Aeropuerto> buscarAeropuertosPorCodigoIATA(String codigoIATA) {
        return aeropuertoRepository.findByCodigoIATA(codigoIATA);
    }

    @Override
    public Optional<Aeropuerto> actualizarAeropuerto(Long id, Aeropuerto aeropuerto) {
        return aeropuertoRepository.findById(id).map(oldAeropuerto -> {
            oldAeropuerto.setNombre(aeropuerto.getNombre());
            oldAeropuerto.setCodigoIATA(aeropuerto.getCodigoIATA());
            oldAeropuerto.setCiudad(aeropuerto.getCiudad());
            oldAeropuerto.setPais(aeropuerto.getPais());
            return aeropuertoRepository.save(oldAeropuerto);
        });
    }

    @Override
    public void eliminarAeropuerto(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}
