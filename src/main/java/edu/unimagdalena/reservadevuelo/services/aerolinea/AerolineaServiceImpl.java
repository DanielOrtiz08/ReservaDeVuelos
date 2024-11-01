package edu.unimagdalena.reservadevuelo.services.aerolinea;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
import edu.unimagdalena.reservadevuelo.mappers.AerolineaMapper;
import edu.unimagdalena.reservadevuelo.repositories.AerolineaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AerolineaServiceImpl implements AerolineaService {
    private final AerolineaRepository aerolineaRepository;
    private final AerolineaMapper aerolineaMapper;

    @Override
    public AerolineaDto guardarAerolinea(AerolineaDto aerolineaDto) {
        Aerolinea aerolineaEntity = aerolineaMapper.aerolineaDtoToAerolinea(aerolineaDto);
        Aerolinea savedAerolinea = aerolineaRepository.save(aerolineaEntity);
        return aerolineaMapper.aerolineaToAerolineaDto(savedAerolinea);
    }

    @Override
    public Optional<AerolineaDto> buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id)
                .map(aerolineaMapper::aerolineaToAerolineaDto);
    }

    @Override
    public List<AerolineaDto> buscarAerolineas() {
        List<Aerolinea> aerolineas =  aerolineaRepository.findAll();
        return aerolineaMapper.aerolienaToAerolienaDto(aerolineas);
    }

    @Override
    public List<AerolineaDto> buscarAerolineasPorNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre)
                .stream().map(aerolineaMapper::aerolineaToAerolineaDto).toList();
    }

    @Override
    public Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolineaDto) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            Aerolinea aerolinea = aerolineaMapper.aerolineaDtoToAerolinea(aerolineaDto);
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisOrigen(aerolinea.getPaisOrigen());
            Aerolinea updatedAerolinea = aerolineaRepository.save(oldAerolinea);
            return aerolineaMapper.aerolineaToAerolineaDto(updatedAerolinea);
        });
    }

    @Override
    public void eliminarAerolinea(Long id) {
        aerolineaRepository.deleteById(id);
    }

    @Override
    public boolean existeAerolineaPorId(Long id) {
        return aerolineaRepository.existsById(id);
    }
}
