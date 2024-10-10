package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.entities.Aerolinea;
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
    public ResponseEntity<List<AerolineaDto>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolineas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AerolineaDto> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaPorId(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<AerolineaDto> getAerolineaByName(@PathVariable String name) {
        return aerolineaService.buscarAerolineasPorNombre(name)
                .stream().map(a -> ResponseEntity.ok().body(a))
                .findAny().orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AerolineaDto> createAerolinea(@RequestBody AerolineaDto aerolineaDto) throws URISyntaxException {
        return createNewAerolinea(aerolineaDto);
    }

    public ResponseEntity<AerolineaDto> createNewAerolinea(AerolineaDto aerolineaDto){
        AerolineaDto newAerolinea = aerolineaService.guardarAerolinea(aerolineaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolinea.id())
                .toUri();
        return ResponseEntity.created(location).body(newAerolinea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AerolineaDto> updateAerolinea(@PathVariable Long id, @RequestBody AerolineaDto aerolineaDto) {
        Optional<AerolineaDto> updatedAerolinea = aerolineaService.actualizarAerolinea(id, aerolineaDto);
        return updatedAerolinea
                .map(a -> ResponseEntity.ok(a))
                .orElseGet(() -> {
                    return createNewAerolinea(aerolineaDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
        aerolineaService.eliminarAerolinea(id);
        return ResponseEntity.noContent().build();
    }
}