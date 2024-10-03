package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import edu.unimagdalena.reservadevuelo.services.PasajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/pasajero")
@RestController
public class PasajeroController {
    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping
    public ResponseEntity<List<Pasajero>> allPasajeros(){
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pasajero> pasajeroById(Long id){
        return pasajeroService.buscarPasajeroPorId(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Pasajero>> pasajeroByNombre(String nombre){
        return ResponseEntity.ok(pasajeroService.buscarPasajerosPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero){
        return ResponseEntity.ok(pasajeroService.guardarPasajero(pasajero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasajero> updatePasajero(@PathVariable Long id, @RequestBody Pasajero pasajero){
        return pasajeroService.actualizarPasajero(id, pasajero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pasajero> deletePasajero(@PathVariable Long id){
        return pasajeroService.buscarPasajeroPorId(id)
                .map(p -> {
                    pasajeroService.eliminarPasajero(id);
                    return ResponseEntity.ok().body(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
