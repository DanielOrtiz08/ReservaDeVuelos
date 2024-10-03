package edu.unimagdalena.reservadevuelo.services;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.dto.ClienteMapper;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import edu.unimagdalena.reservadevuelo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> buscarClientesPorIds(List<Long> ids) {
        return clienteRepository.findByIdIn(ids);
    }

    @Override
    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Cliente> actualizarCliente(Long id, Cliente cliente) {
        return clienteRepository.findById(id).map(oldClient -> {
            oldClient.setNombre(cliente.getNombre());
            oldClient.setApellido(cliente.getApellido());
            oldClient.setCorreoElectronico(cliente.getCorreoElectronico());
            oldClient.setDireccion(oldClient.getDireccion());
            oldClient.setTelefono(cliente.getTelefono());
            oldClient.setFechaNacimiento(cliente.getFechaNacimiento());
            return clienteRepository.save(oldClient);
        });
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
