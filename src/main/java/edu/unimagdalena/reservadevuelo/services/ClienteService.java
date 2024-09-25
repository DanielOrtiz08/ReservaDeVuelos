package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    Optional<Cliente> buscarClientePorId(Long id);
    List<Cliente> buscarClientes();
    List<Cliente> buscarClientesPorIds(List<Long> ids);
    List<Cliente> buscarClientesPorNombre(String nombre);
    Optional<Cliente> actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
