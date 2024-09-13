package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Aerolinea;
import edu.unimagdalena.reservadevuelo.repositories.AerolineaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private AerolineaRepository aerolineaRepository;

    @Override
    public Aerolinea guardarAerolinea(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    public Optional<Aerolinea> buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id);
    }

    @Override
    public List<Aerolinea> buscarAerolineas() {
        return aerolineaRepository.findAll();
    }

    @Override
    public List<Aerolinea> buscarAerolineasPorNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisOrigen(aerolinea.getPaisOrigen());
            return aerolineaRepository.save(oldAerolinea);
        });
    }
}
