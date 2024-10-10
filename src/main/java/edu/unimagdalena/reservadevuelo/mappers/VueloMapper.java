package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VueloMapper {

    public static final VueloMapper mapper = Mappers.getMapper(VueloMapper.class);

    Vuelo vueloDtoToVuelo(VueloDto vueloDto);

    VueloDto vueloToVueloDto(Vuelo vuelo);

    List<VueloDto> vueloToVueloDto(List<Vuelo> vuelos);

    List<Vuelo> vueloDtoToVuelo(List<VueloDto> vueloDto);
}
