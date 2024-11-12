package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    Reserva reservaDtoToReserva(ReservaDto reservaDto);
    @InheritInverseConfiguration
    ReservaDto reservaToReservaDto(Reserva reserva);

    List<ReservaDto> reservaToReservaDto(List<Reserva> reservas);

    List<Reserva> reservaDtoToReserva(List<ReservaDto> reservaDto);
}

