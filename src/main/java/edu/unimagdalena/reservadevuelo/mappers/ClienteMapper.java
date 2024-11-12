package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente clienteDtoToCliente(ClienteDto clienteDto);
    ClienteDto clienteToClienteDto(Cliente cliente);

    List<ClienteDto> clienteToClienteDto(List<Cliente> clientes);

    List<Cliente> clienteDtoToCliente(List<ClienteDto> clienteDtos);
}



