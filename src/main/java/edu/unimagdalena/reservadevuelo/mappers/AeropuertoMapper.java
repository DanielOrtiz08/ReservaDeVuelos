package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.AeropuertoDto;
import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AeropuertoMapper {

    AeropuertoMapper INSTANCE = Mappers.getMapper(AeropuertoMapper.class);

    Aeropuerto aeropuertoDtoToAeropuerto(AeropuertoDto aeropuertoDto);
    AeropuertoDto aeropuertoToAeropuertoDto(Aeropuerto aeropuerto);

    List<AeropuertoDto> aeropuertoToAeropuertoDto(List<Aeropuerto> aeropuertos);

}
