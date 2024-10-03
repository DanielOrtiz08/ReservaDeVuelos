package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    List<Aerolinea> findByNombre(String nombre);
}
