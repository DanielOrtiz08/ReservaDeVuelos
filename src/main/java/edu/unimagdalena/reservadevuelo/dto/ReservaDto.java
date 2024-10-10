package edu.unimagdalena.reservadevuelo.dto;

import edu.unimagdalena.reservadevuelo.entities.Cliente;

import java.time.LocalDate;

public record ReservaDto(Long id,
                         LocalDate fechaReseva,
                         Integer numeroPasajero,
                         Cliente cliente) {
}
