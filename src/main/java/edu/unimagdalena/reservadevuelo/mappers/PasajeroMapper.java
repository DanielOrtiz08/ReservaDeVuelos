package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.PasajeroDto;
import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PasajeroMapper {

    PasajeroMapper INSTANCE = Mappers.getMapper(PasajeroMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "nombre", source = "nombre"),
            @Mapping(target = "apellido", source = "apellido"),
            @Mapping(target = "documentoIdentificacion", source = "documentoIdentificacion"),
            @Mapping(target = "nacionalidad", source = "nacionalidad"),
            @Mapping(target = "direccion", source = "direccion"),
            @Mapping(target = "telefono", source = "telefono"),
            @Mapping(target = "correoElectronico", source = "correoElectronico"),
            @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    })
    Pasajero pasajeroDtoToPasajero(PasajeroDto pasajeroDto);
    @InheritInverseConfiguration
    PasajeroDto pasajeroToPasajeroDto(Pasajero pasajero);

    List<PasajeroDto> pasajeroToPasajeroDto(List<Pasajero> pasajeros);

    List<Pasajero> pasajeroDtoToPasajero(List<PasajeroDto> pasajeroDto);
}

