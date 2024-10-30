package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AerolineaMapper {

    public static final AerolineaMapper mapper = Mappers.getMapper(AerolineaMapper.class);

    Aerolinea aerolineaDtoToAerolinea(AerolineaDto aerolineaDto);

    AerolineaDto aerolineaToAerolineaDto(Aerolinea aerolinea);

    List<AerolineaDto> aerolienaToAerolienaDto(List<Aerolinea> aerolineas);

    List<Aerolinea> aerolineaDtoToAerolinea(List<AerolineaDto> aerolineasDto);


}
