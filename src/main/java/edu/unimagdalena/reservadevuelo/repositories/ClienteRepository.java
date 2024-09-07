package edu.unimagdalena.reservadevuelo.repositories;

import edu.unimagdalena.reservadevuelo.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //@Query("SELECT c FROM CLIENTES c WHERE c.id IN ?>1")
    List<Cliente> findByNombre(String nombre);
    //@Query("SELECT c FROM Cliente c WHERE c.id IN ?1")
    List<Cliente> findByIdIn(Collection<Long> ids);
}
