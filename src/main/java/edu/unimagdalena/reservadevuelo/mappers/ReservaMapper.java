package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservaMapper {
    public static final ReservaMapper mapper = Mappers.getMapper(ReservaMapper.class);

    Reserva reservaDtoToReserva(ReservaDto reservaDto);

    ReservaDto reservaToReservaDto(Reserva reserva);

    List<ReservaDto> reservaToReservaDto(List<Reserva> reservas);

    List<Reserva> reservaDtoToReserva(List<ReservaDto> reservaDto);
}
