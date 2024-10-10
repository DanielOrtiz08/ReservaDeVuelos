package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
import edu.unimagdalena.reservadevuelo.mappers.AerolineaMapper;
import edu.unimagdalena.reservadevuelo.repositories.AerolineaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private AerolineaRepository aerolineaRepository;

    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public AerolineaDto guardarAerolinea(AerolineaDto aerolineaDto) {
        Aerolinea aerolineaEntity = AerolineaMapper.mapper.aerolineaDtoToAerolinea(aerolineaDto);
        Aerolinea savedAerolinea = aerolineaRepository.save(aerolineaEntity);
        return AerolineaMapper.mapper.aerolineaToAerolineaDto(savedAerolinea);
    }

    @Override
    public Optional<AerolineaDto> buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id)
                .map(AerolineaMapper.mapper::aerolineaToAerolineaDto);
    }

    @Override
    public List<AerolineaDto> buscarAerolineas() {
        List<Aerolinea> aerolineas =  aerolineaRepository.findAll();
        return AerolineaMapper.mapper.aerolienaToAerolienaDto(aerolineas);
    }

    @Override
    public List<AerolineaDto> buscarAerolineasPorNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre)
                .stream().map(AerolineaMapper.mapper::aerolineaToAerolineaDto).toList();
    }

    @Override
    public Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolineaDto) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            Aerolinea aerolinea = AerolineaMapper.mapper.aerolineaDtoToAerolinea(aerolineaDto);
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisOrigen(aerolinea.getPaisOrigen());
            Aerolinea updatedAerolinea = aerolineaRepository.save(oldAerolinea);
            return AerolineaMapper.mapper.aerolineaToAerolineaDto(updatedAerolinea);
        });
    }

    @Override
    public void eliminarAerolinea(Long id) {
        aerolineaRepository.deleteById(id);
    }
}
