package edu.unimagdalena.reservadevuelo.services.cliente;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.mappers.ClienteMapper;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import edu.unimagdalena.reservadevuelo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        Cliente clienteEntity = clienteMapper.clienteDtoToCliente(clienteDto); // Convertimos el DTO a entidad
        Cliente savedCliente = clienteRepository.save(clienteEntity); // Guardamos la entidad en la base de datos
        return clienteMapper.clienteToClienteDto(savedCliente); // Retornamos el DTO de la entidad guardada
    }

    @Override
    public Optional<ClienteDto> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDto); // Mapeamos la entidad a DTO si existe
    }

    @Override
    public List<ClienteDto> buscarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.clienteToClienteDto(clientes); // Convertimos la lista de entidades a DTOs
    }

    @Override
    public List<ClienteDto> buscarClientesPorIds(List<Long> ids) {
        List<Cliente> clientes = clienteRepository.findByIdIn(ids);
        return clienteMapper.clienteToClienteDto(clientes); // Convertimos la lista de entidades a DTOs
    }

    @Override
    public List<ClienteDto> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombre(nombre);
        return clienteMapper.clienteToClienteDto(clientes); // Convertimos la lista de entidades a DTOs
    }

    @Override
    public Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id).map(existingCliente -> {
            Cliente clienteToUpdate = clienteMapper.clienteDtoToCliente(clienteDto); // Convertimos el DTO a entidad
            existingCliente.setNombre(clienteToUpdate.getNombre());
            existingCliente.setApellido(clienteToUpdate.getApellido());
            existingCliente.setCorreoElectronico(clienteToUpdate.getCorreoElectronico());
            existingCliente.setDireccion(clienteToUpdate.getDireccion());
            existingCliente.setTelefono(clienteToUpdate.getTelefono());
            existingCliente.setFechaNacimiento(clienteToUpdate.getFechaNacimiento());
            Cliente updatedCliente = clienteRepository.save(existingCliente); // Guardamos los cambios
            return clienteMapper.clienteToClienteDto(updatedCliente); // Retornamos el DTO actualizado
        });
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
