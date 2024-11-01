package edu.unimagdalena.reservadevuelo.services.cliente;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDto guardarCliente(ClienteDto clienteDto);
    Optional<ClienteDto> buscarClientePorId(Long id);
    List<ClienteDto> buscarClientes();
    List<ClienteDto> buscarClientesPorIds(List<Long> ids);
    List<ClienteDto> buscarClientesPorNombre(String nombre);
    Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto);
    void eliminarCliente(Long id);
    boolean existeClienteConCorreoElectronico(String correoElectronico);
    boolean existeClientePorId(Long id);
}
