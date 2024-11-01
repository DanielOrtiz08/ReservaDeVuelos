package edu.unimagdalena.reservadevuelo.dto;

import java.time.LocalDate;

public record ReservaDto(Long id,
                         LocalDate fechaReserva,
                         Integer numeroPasajero) {
}
