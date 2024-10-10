package edu.unimagdalena.reservadevuelo.dto;

public record AeropuertoDto(Long id,
                            String nombre,
                            String ciudad,
                            String pais,
                            String codigoIATA) {
}
