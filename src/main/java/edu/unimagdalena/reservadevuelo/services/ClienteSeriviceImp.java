package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.Entities.Cliente;
//import edu.unimagdalena.reservadevuelo.repositories.ClienteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteSeriviceImp implements ClienteService {

    //@Autowired private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cliente> buscarClientes() {
        return List.of();
    }

    @Override
    public List<Cliente> buscarClientesByIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public List<Cliente> buscarClientesByNombre(String nombre) {
        return List.of();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return null;
    }
}
