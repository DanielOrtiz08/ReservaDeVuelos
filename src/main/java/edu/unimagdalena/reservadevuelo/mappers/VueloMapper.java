package edu.unimagdalena.reservadevuelo.mappers;

import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VueloMapper {

    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    VueloDto vueloToVueloDto(Vuelo vuelo);
    @InheritInverseConfiguration
    Vuelo vueloDtoToVuelo(VueloDto vueloDto);

    List<VueloDto> vueloToVueloDto(List<Vuelo> vuelos);

    List<Vuelo> vueloDtoToVuelo(List<VueloDto> vueloDto);
}

