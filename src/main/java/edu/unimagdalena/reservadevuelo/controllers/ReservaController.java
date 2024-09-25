package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.Entities.Reserva;
import edu.unimagdalena.reservadevuelo.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/reserva")
@RestController
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> allReservas(){
        return ResponseEntity.ok(reservaService.buscarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id){
        return reservaService.buscarReservaPorId(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Reserva>> getReservasByCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(reservaService.buscarReservasPorCliente(clienteId));
    }

    @GetMapping("/vuelo/{vueloId}")
    public ResponseEntity<List<Reserva>> getReservasByVuelo(@PathVariable Long vueloId){
        return ResponseEntity.ok(reservaService.buscarReservasPorVuelo(vueloId));
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva){
        return ResponseEntity.ok(reservaService.guardarReserva(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva){
        return reservaService.actualizarReserva(id, reserva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
