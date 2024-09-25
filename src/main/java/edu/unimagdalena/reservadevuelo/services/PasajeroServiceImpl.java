package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Pasajero;
import edu.unimagdalena.reservadevuelo.repositories.PasajeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {

    private PasajeroRepository pasajeroRepository;

    @Override
    public Pasajero guardarPasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    @Override
    public Optional<Pasajero> buscarPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id);
    }

    @Override
    public List<Pasajero> buscarPasajeros() {
        return pasajeroRepository.findAll();
    }

    @Override
    public List<Pasajero> buscarPasajerosPorNombre(String nombre) {
        return pasajeroRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Pasajero> buscarPorDocumentoIdentificacion(String documentoIdentificacion) {
        return pasajeroRepository.findByDocumentoIdentificacion(documentoIdentificacion);
    }

    @Override
    public Optional<Pasajero> actualizarPasajero(Long id, Pasajero pasajero) {
        return pasajeroRepository.findById(id).map(existingPasajero -> {
            existingPasajero.setNombre(pasajero.getNombre());
            existingPasajero.setApellido(pasajero.getApellido());
            existingPasajero.setDocumentoIdentificacion(pasajero.getDocumentoIdentificacion());
            existingPasajero.setNacionalidad(pasajero.getNacionalidad());
            existingPasajero.setDireccion(pasajero.getDireccion());
            existingPasajero.setTelefono(pasajero.getTelefono());
            existingPasajero.setCorreoElectronico(pasajero.getCorreoElectronico());
            existingPasajero.setFechaNacimiento(pasajero.getFechaNacimiento());
            return pasajeroRepository.save(existingPasajero);
        });
    }

    @Override
    public void eliminarPasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }
}
