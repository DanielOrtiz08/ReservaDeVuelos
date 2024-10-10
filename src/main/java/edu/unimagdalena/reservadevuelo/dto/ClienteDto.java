package edu.unimagdalena.reservadevuelo.dto;

import java.time.LocalDate;

public record ClienteDto(Long id,
                         String nombre,
                         String apellido,
                         String direccion,
                         String telefono,
                         String correoElectronico,
                         LocalDate fechaNacimiento) {
}
