package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findByNombre(String nombre);
    Optional<Pasajero> findByDocumentoIdentificacion(String documentoIdentificacion);
}
