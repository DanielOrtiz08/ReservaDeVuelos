package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    Optional<Cliente> buscarClientePorId(Long id);
    List<Cliente> buscarClientes();
    List<Cliente> buscarClientesByIds(List<Long> ids);
    List<Cliente> buscarClientesByNombre(String nombre);
    Cliente actualizarCliente(Cliente cliente);
}
