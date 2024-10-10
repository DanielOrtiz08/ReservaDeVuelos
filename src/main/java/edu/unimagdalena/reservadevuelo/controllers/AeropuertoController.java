package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.entities.Aeropuerto;
import edu.unimagdalena.reservadevuelo.services.AeropuertoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuerto")
public class AeropuertoController {
    private AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService){
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping
    public ResponseEntity<List<Aeropuerto>> getAllAeropuertos(){
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aeropuerto> getAeropuertoById(@PathVariable Long id){
        return aeropuertoService.buscarAeropuertoPorId(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Aeropuerto>> getAeropuertoByNombre(@PathVariable String nombre){
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertosPorNombre(nombre));
    }

    @PostMapping()
    public ResponseEntity<Aeropuerto> createAeropuerto(@RequestBody Aeropuerto aeropuerto) throws URISyntaxException {
        return createNewAeropuerto(aeropuerto);
    }

    public ResponseEntity<Aeropuerto> createNewAeropuerto(Aeropuerto aeropuerto){
        Aeropuerto newAeropuerto = aeropuertoService.guardarAeropuerto(aeropuerto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(newAeropuerto.getId()).
                toUri();
        return ResponseEntity.created(location).body(newAeropuerto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aeropuerto> updateAeropuerto(@PathVariable Long id, @RequestBody Aeropuerto aeropuerto) {
        Optional<Aeropuerto> aeropuertoUpdate = aeropuertoService.actualizarAeropuerto(id, aeropuerto);
        return aeropuertoUpdate
                .map(a -> ResponseEntity.ok(a))
                .orElseGet(() -> {
                    return createNewAeropuerto(aeropuerto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id){
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }
}
