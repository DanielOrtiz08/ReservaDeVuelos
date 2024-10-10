package edu.unimagdalena.reservadevuelo.dto;

public record PasajeroDto(Long id,
                          String nombre,
                          String apellido,
                          String documentoIdentificacion,
                          String nacionalidad,
                          String direccion,
                          String telefono,
                          String correoElectronico,
                          String fechaNacimiento) {
}
