package edu.unimagdalena.reservadevuelo.services.aeropuerto;

import edu.unimagdalena.reservadevuelo.dto.AeropuertoDto;
import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import edu.unimagdalena.reservadevuelo.mappers.AeropuertoMapper;
import edu.unimagdalena.reservadevuelo.repositories.AeropuertoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AeropuertoServiceImpl implements AeropuertoService {

    private final AeropuertoRepository aeropuertoRepository;
    private final AeropuertoMapper aeropuertoMapper;

    @Override
    public AeropuertoDto guardarAeropuerto(AeropuertoDto aeropuertoDto) {
        Aeropuerto aeropuerto = aeropuertoMapper.aeropuertoDtoToAeropuerto(aeropuertoDto);
        Aeropuerto savedAeropuerto = aeropuertoRepository.save(aeropuerto);
        return aeropuertoMapper.aeropuertoToAeropuertoDto(savedAeropuerto);
    }

    @Override
    public Optional<AeropuertoDto> buscarAeropuertoPorId(Long id) {
        return aeropuertoRepository.findById(id).
                map(aeropuertoMapper::aeropuertoToAeropuertoDto);
    }

    @Override
    public List<AeropuertoDto> buscarAeropuertos() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        return aeropuertoMapper.aeropuertoToAeropuertoDto(aeropuertos);
    }

    @Override
    public List<AeropuertoDto> buscarAeropuertosPorNombre(String nombre) {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findByNombre(nombre);
        return aeropuertoMapper.aeropuertoToAeropuertoDto(aeropuertos);
    }

    public Optional<AeropuertoDto> buscarAeropuertoPorCodigoIATA(String codigoIATA) {
        return aeropuertoRepository.findByCodigoIATA(codigoIATA)
                .map(aeropuertoMapper::aeropuertoToAeropuertoDto);
    }

    @Override
    public Optional<AeropuertoDto> actualizarAeropuerto(Long id, AeropuertoDto AeropuertoDto) {
        return aeropuertoRepository.findById(id).map(oldAeropuerto -> {
            Aeropuerto aeropuertoToUpdate = aeropuertoMapper.aeropuertoDtoToAeropuerto(AeropuertoDto);
            oldAeropuerto.setNombre(aeropuertoToUpdate.getNombre());
            oldAeropuerto.setCodigoIATA(aeropuertoToUpdate.getCodigoIATA());
            oldAeropuerto.setCiudad(aeropuertoToUpdate.getCiudad());
            oldAeropuerto.setPais(aeropuertoToUpdate.getPais());
            Aeropuerto updateAeropuerto = aeropuertoRepository.save(oldAeropuerto);
            return aeropuertoMapper.aeropuertoToAeropuertoDto(updateAeropuerto);
        });
    }

    @Override
    public void eliminarAeropuerto(Long id) {
        aeropuertoRepository.deleteById(id);
    }

    @Override
    public boolean existeAeropuertoPorId(Long id) {
        return aeropuertoRepository.existsById(id);
    }
}
