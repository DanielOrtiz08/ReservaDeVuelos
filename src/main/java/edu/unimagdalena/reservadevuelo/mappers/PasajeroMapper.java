package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.PasajeroDto;
import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PasajeroMapper {

    public static final PasajeroMapper mapper = Mappers.getMapper(PasajeroMapper.class);

    Pasajero pasajeroDtoToPasajero(PasajeroDto pasajeroDto);

    PasajeroDto pasajeroToPasajeroDto(Pasajero pasajero);

    List<PasajeroDto> pasajeroToPasajeroDto(List<Pasajero> pasajeros);

    List<Pasajero> pasajeroDtoToPasajero(List<PasajeroDto> pasajeroDto);

}
