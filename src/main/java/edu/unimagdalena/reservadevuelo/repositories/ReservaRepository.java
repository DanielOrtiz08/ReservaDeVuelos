package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente_Id(Long clienteId);
    List<Reserva> findByVuelos_Id(Long vueloId);
}
