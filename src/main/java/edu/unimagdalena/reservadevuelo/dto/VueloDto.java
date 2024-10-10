package edu.unimagdalena.reservadevuelo.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record VueloDto(Long id,
                       String origen,
                       String destino,
                       LocalDate fechaSalida,
                       LocalTime horaSalida,
                       Duration duracion,
                       Integer capacidad) {
}
