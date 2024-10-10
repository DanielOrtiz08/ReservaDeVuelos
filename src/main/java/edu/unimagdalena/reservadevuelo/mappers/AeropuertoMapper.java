package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.AeropuertoDto;
import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AeropuertoMapper {

    public static final AeropuertoMapper mapper = Mappers.getMapper(AeropuertoMapper.class);

    Aeropuerto aeropueroDtoToAeropuerto(AeropuertoDto aeropuertoDto);

    AeropuertoDto aeropuertoToAeropuertoDto(Aeropuerto aeropuerto);
}
