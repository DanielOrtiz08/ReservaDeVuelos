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

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "nombre", source = "nombre"),
            @Mapping(target = "apellido", source = "apellido"),
            @Mapping(target = "direccion", source = "direccion"),
            @Mapping(target = "telefono", source = "telefono"),
            @Mapping(target = "correoElectronico", source = "correoElectronico"),
            @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    })
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
    @InheritInverseConfiguration
    ClienteDto clienteToClienteDto(Cliente cliente);

    List<ClienteDto> clienteToClienteDto(List<Cliente> clientes);

    List<Cliente> clienteDtoToCliente(List<ClienteDto> clienteDtos);
}



