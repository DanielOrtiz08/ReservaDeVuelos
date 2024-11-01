package edu.unimagdalena.reservadevuelo.services.pasajero;

import edu.unimagdalena.reservadevuelo.dto.PasajeroDto;
import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import edu.unimagdalena.reservadevuelo.mappers.PasajeroMapper;
import edu.unimagdalena.reservadevuelo.repositories.PasajeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasajeroServiceImpl implements PasajeroService {

    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;

    @Override
    public PasajeroDto guardarPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajeroEntity = pasajeroMapper.pasajeroDtoToPasajero(pasajeroDto);
        Pasajero savedPasajero = pasajeroRepository.save(pasajeroEntity);
        return pasajeroMapper.pasajeroToPasajeroDto(savedPasajero);
    }

    @Override
    public Optional<PasajeroDto> buscarPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id)
                .map(pasajeroMapper::pasajeroToPasajeroDto);
    }

    @Override
    public List<PasajeroDto> buscarPasajeros() {
        List<Pasajero> pasajeros = pasajeroRepository.findAll();
        return pasajeroMapper.pasajeroToPasajeroDto(pasajeros);
    }

    @Override
    public List<PasajeroDto> buscarPasajerosPorNombre(String nombre) {
        List<Pasajero> pasajeros = pasajeroRepository.findByNombre(nombre);
        return pasajeroMapper.pasajeroToPasajeroDto(pasajeros);
    }

    @Override
    public Optional<PasajeroDto> buscarPorDocumentoIdentificacion(String documentoIdentificacion) {
        return pasajeroRepository.findByDocumentoIdentificacion(documentoIdentificacion)
                .map(pasajeroMapper::pasajeroToPasajeroDto);    }

    @Override
    public Optional<PasajeroDto> actualizarPasajero(Long id, PasajeroDto pasajeroDto) {
        return pasajeroRepository.findById(id).map(existingPasajero -> {
            Pasajero pasajeroToUpdate = pasajeroMapper.pasajeroDtoToPasajero(pasajeroDto);
            existingPasajero.setNombre(pasajeroToUpdate.getNombre());
            existingPasajero.setApellido(pasajeroToUpdate.getApellido());
            existingPasajero.setDocumentoIdentificacion(pasajeroToUpdate.getDocumentoIdentificacion());
            existingPasajero.setNacionalidad(pasajeroToUpdate.getNacionalidad());
            existingPasajero.setDireccion(pasajeroToUpdate.getDireccion());
            existingPasajero.setTelefono(pasajeroToUpdate.getTelefono());
            existingPasajero.setCorreoElectronico(pasajeroToUpdate.getCorreoElectronico());
            existingPasajero.setFechaNacimiento(pasajeroToUpdate.getFechaNacimiento());
            return pasajeroMapper.pasajeroToPasajeroDto(pasajeroRepository.save(existingPasajero));
        });
    }


    @Override
    public void eliminarPasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }

    @Override
    public boolean existePasajeroPorId(Long id) {
        return pasajeroRepository.existsById(id);
    }
}