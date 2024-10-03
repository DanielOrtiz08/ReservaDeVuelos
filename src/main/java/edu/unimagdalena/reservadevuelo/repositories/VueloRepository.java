package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByOrigen(String origen);
    List<Vuelo> findByDestino(String destino);
    List<Vuelo> findByAeropuerto_Id(Long aeropuertoId);
    List<Vuelo> findByAerolinea_Id(Long aerolineaId);
}
