package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AerolineaMapper {

    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "nombre", source = "nombre"),
            @Mapping(target = "codigoAerolinea", source = "codigoAerolinea"),
            @Mapping(target = "paisOrigen", source = "paisOrigen")
    })
    Aerolinea aerolineaDtoToAerolinea(AerolineaDto aerolineaDto);
    @InheritInverseConfiguration
    AerolineaDto aerolineaToAerolineaDto(Aerolinea aerolinea);

    List<AerolineaDto> aerolienaToAerolienaDto(List<Aerolinea> aerolineas);

    List<Aerolinea> aerolineaDtoToAerolinea(List<AerolineaDto> aerolineasDto);
}
