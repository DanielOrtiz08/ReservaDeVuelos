package edu.unimagdalena.reservadevuelo.services.cliente;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.mappers.ClienteMapper;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import edu.unimagdalena.reservadevuelo.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        Cliente clienteEntity = clienteMapper.clienteDtoToCliente(clienteDto);
        Cliente savedCliente = clienteRepository.save(clienteEntity);
        return clienteMapper.clienteToClienteDto(savedCliente);
    }

    @Override
    public Optional<ClienteDto> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDto);
    }

    @Override
    public List<ClienteDto> buscarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.clienteToClienteDto(clientes);
    }

    @Override
    public List<ClienteDto> buscarClientesPorIds(List<Long> ids) {
        List<Cliente> clientes = clienteRepository.findByIdIn(ids);
        return clienteMapper.clienteToClienteDto(clientes);
    }

    @Override
    public List<ClienteDto> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombre(nombre);
        return clienteMapper.clienteToClienteDto(clientes);
    }

    @Override
    public Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id).map(existingCliente -> {
            Cliente clienteToUpdate = clienteMapper.clienteDtoToCliente(clienteDto);
            existingCliente.setNombre(clienteToUpdate.getNombre());
            existingCliente.setApellido(clienteToUpdate.getApellido());
            existingCliente.setCorreoElectronico(clienteToUpdate.getCorreoElectronico());
            existingCliente.setDireccion(clienteToUpdate.getDireccion());
            existingCliente.setTelefono(clienteToUpdate.getTelefono());
            existingCliente.setFechaNacimiento(clienteToUpdate.getFechaNacimiento());
            Cliente updatedCliente = clienteRepository.save(existingCliente);
            return clienteMapper.clienteToClienteDto(updatedCliente);
        });
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existeClienteConCorreoElectronico(String correoElectronico) {
        return clienteRepository.existsByCorreoElectronico(correoElectronico);
    }

    @Override
    public boolean existeClientePorId(Long id) {
        return  clienteRepository.existsById(id);
    }
}
