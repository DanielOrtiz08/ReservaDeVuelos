package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.ReservaDto;
import edu.unimagdalena.reservadevuelo.entities.Reserva;
import edu.unimagdalena.reservadevuelo.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/reserva")
@RestController
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getAllReservas(){
        return ResponseEntity.ok(reservaService.buscarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long id){
        return reservaService.buscarReservaPorId(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ReservaDto>> getReservasByCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(reservaService.buscarReservasPorCliente(clienteId));
    }

    @GetMapping("/vuelo/{vueloId}")
    public ResponseEntity<List<ReservaDto>> getReservasByVuelo(@PathVariable Long vueloId){
        return ResponseEntity.ok(reservaService.buscarReservasPorVuelo(vueloId));
    }

    @PostMapping()
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

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long id, @RequestBody ReservaDto reservaDto){
        Optional<ReservaDto> reservaUpdate = reservaService.buscarReservaPorId(id);
        return reservaUpdate
                .map(r -> ResponseEntity.ok(r))
                .orElseGet(() -> {
                    return createNewReserva(reservaDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
