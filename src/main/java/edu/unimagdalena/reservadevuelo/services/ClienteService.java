package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDto guardarCliente(ClienteDto cliente);
    Optional<ClienteDto> buscarClientePorId(Long id);
    List<ClienteDto> buscarClientes();
    List<ClienteDto> buscarClientesPorIds(List<Long> ids);
    List<ClienteDto> buscarClientesPorNombre(String nombre);
    Optional<ClienteDto> actualizarCliente(Long id, ClienteDto cliente);
    void eliminarCliente(Long id);
}
