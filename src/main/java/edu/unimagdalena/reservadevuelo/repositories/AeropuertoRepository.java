package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
    List<Aeropuerto> findByNombre(String nombre);
    List<Aeropuerto> findByCodigoIATA(String codigoIATA);
}
