package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.Entities.Aerolinea;
import edu.unimagdalena.reservadevuelo.services.AerolineaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
public class AerolineaController {
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping()
    public ResponseEntity<List<Aerolinea>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolineas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aerolinea> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaPorId(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Aerolinea> createAerolinea(@RequestBody Aerolinea aerolinea) throws URISyntaxException {
        Aerolinea newAerolinea = aerolineaService.guardarAerolinea(aerolinea);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolinea.getId())
                .toUri();
        return ResponseEntity.created(location).body(newAerolinea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aerolinea> updateAerolinea(@PathVariable Long id, @RequestBody Aerolinea aerolinea) {
        Optional<Aerolinea> updatedAerolinea = aerolineaService.actualizarAerolinea(id, aerolinea);
        return updatedAerolinea
                .map(a -> ResponseEntity.ok(a))
                .orElseGet(() -> {
                    try {
                        return createAerolinea(aerolinea);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAerolinea(@PathVariable Long id) {
        aerolineaService.eliminarAerolinea(id);
        return ResponseEntity.noContent().build();
    }
}