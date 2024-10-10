package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperManual {
    public Cliente toEntity(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.nombre());
        cliente.setApellido(clienteDto.apellido());
        cliente.setDireccion(clienteDto.direccion());
        cliente.setCorreoElectronico(clienteDto.correoElectronico());
        cliente.setFechaNacimiento(clienteDto.fechaNacimiento());
        return cliente;
    }

    public ClienteDto toDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getCorreoElectronico(),
                cliente.getFechaNacimiento());
        return clienteDto;
    }
}
