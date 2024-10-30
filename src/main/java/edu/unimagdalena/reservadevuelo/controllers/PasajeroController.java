package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.entities.Pasajero;
import edu.unimagdalena.reservadevuelo.services.PasajeroService;
import edu.unimagdalena.reservadevuelo.services.ReservaService;
import edu.unimagdalena.reservadevuelo.services.ReservaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/pasajero")
@RestController
public class PasajeroController {
    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService, ReservaServiceImpl reservaServiceImpl) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping
    public ResponseEntity<List<Pasajero>> allPasajeros(){
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pasajero> pasajeroById(@PathVariable Long id){
        return pasajeroService.buscarPasajeroPorId(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Pasajero>> pasajeroByNombre(@PathVariable String nombre){
        return ResponseEntity.ok(pasajeroService.buscarPasajerosPorNombre(nombre));
    }

    @PostMapping()
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero) throws URISyntaxException {
        return createNewPasajero(pasajero);
    }

    private ResponseEntity<Pasajero> createNewPasajero(Pasajero pasajero){
        Pasajero newPasajero = pasajeroService.guardarPasajero(pasajero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPasajero.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPasajero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasajero> updatePasajero(@PathVariable Long id, @RequestBody Pasajero pasajero){
        Optional<Pasajero> pasajeroUpdate = pasajeroService.buscarPasajeroPorId(id);
        return pasajeroUpdate
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> {
                    return createNewPasajero(pasajero);
                });
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
