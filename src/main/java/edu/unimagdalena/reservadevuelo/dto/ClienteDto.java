package edu.unimagdalena.reservadevuelo.dto;

public record ClienteDto(String nombre,
                         String apellido,
                         String direccion,
                         String telefono,
                         String email,
                         String fechaNacimiento) {
}
