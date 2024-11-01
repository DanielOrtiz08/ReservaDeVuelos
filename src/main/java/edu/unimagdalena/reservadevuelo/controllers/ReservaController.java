package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.reserva.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/reserva")
@RestController
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping({""})
    public ResponseEntity<List<ReservaDto>> getAllReservas(){
        List<ReservaDto> reservas = reservaService.buscarReservas();
        if (reservas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron reservas registradas.");
        }
        return ResponseEntity.ok().body(reservas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long id){
        return reservaService.buscarReservaPorId(id)
                .map(reservaDto -> ResponseEntity.ok().body(reservaDto))
                .orElseThrow(() -> new ResourceNotFoundException("Reserva con ID " + id + " no encontrada."));
    }

    @GetMapping("/clienteId/{clienteId}")
    public ResponseEntity<List<ReservaDto>> getReservasByCliente(@PathVariable Long clienteId){
        List<ReservaDto> reservas = reservaService.buscarReservasPorCliente(clienteId);
        if (reservas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron reservas para el cliente con ID " + clienteId);
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/vueloId/{vueloId}")
    public ResponseEntity<List<ReservaDto>> getReservasByVuelo(@PathVariable Long vueloId){
        List<ReservaDto> reservas = reservaService.buscarReservasPorVuelo(vueloId);
        if (reservas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron reservas para el vuelo con ID " + vueloId);
        }
        return ResponseEntity.ok(reservas);
    }

    @PostMapping("")
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) throws URISyntaxException {
        return createNewReserva(reservaDto);
    }

    private ResponseEntity<ReservaDto> createNewReserva(ReservaDto reservaDto){
        ReservaDto newReserva = reservaService.guardarReserva(reservaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.id())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long id, @RequestBody ReservaDto reservaDto){
        Optional<ReservaDto> reservaUpdate = reservaService.actualizarReserva(id, reservaDto);
        return reservaUpdate
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva con ID" + id + " no encontrada para actualizar."));

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        if (!reservaService.existeReservaPorId(id)) {
            throw new ResourceNotFoundException("Reserva con ID " + id + " no encontrada para eliminar.");
        }

        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
