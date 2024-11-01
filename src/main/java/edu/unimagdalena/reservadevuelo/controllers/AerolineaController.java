package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.AerolineaDto;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.aerolinea.AerolineaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
@RequiredArgsConstructor
public class AerolineaController {
    private final AerolineaService aerolineaService;

    @GetMapping("")
    public ResponseEntity<List<AerolineaDto>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolineas());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AerolineaDto> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaPorId(id)
                .map(aerolinea -> ResponseEntity.ok().body(aerolinea))
                .orElseThrow(() -> new ResourceNotFoundException("Aerolínea con ID " + id + " no encontrada."));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AerolineaDto> getAerolineaByName(@PathVariable String name) {
        return aerolineaService.buscarAerolineasPorNombre(name)
                .stream().map(a -> ResponseEntity.ok().body(a))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Aerolínea con nombre " + name + " no encontrada."));
    }

    @PostMapping("")
    public ResponseEntity<AerolineaDto> createAerolinea(@RequestBody AerolineaDto aerolineaDto) {
        if (aerolineaDto.codigoAerolinea() == null)
            return ResponseEntity.badRequest().build();
        return createNewAerolinea(aerolineaDto);
    }

    public ResponseEntity<AerolineaDto> createNewAerolinea(AerolineaDto aerolineaDto){
        AerolineaDto newAerolineaDto = aerolineaService.guardarAerolinea(aerolineaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolineaDto.id())
                .toUri();
        return ResponseEntity.created(location).body(newAerolineaDto);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<AerolineaDto> updateAerolinea(@PathVariable Long id, @RequestBody AerolineaDto aerolineaDto) {
        Optional<AerolineaDto> aerolineaUpdated = aerolineaService.actualizarAerolinea(id, aerolineaDto);
        return aerolineaUpdated
                .map(aerolinea -> ResponseEntity.ok()
                        .body(aerolinea))
                .orElseThrow(() -> new ResourceNotFoundException("Aerolínea con ID " + id + " no encontrada para actualizar."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
        boolean exists = aerolineaService.existeAerolineaPorId(id);
        if (!exists) {
            throw new ResourceNotFoundException("Aerolínea con ID " + id + " no encontrada para eliminar.");
        }
        aerolineaService.eliminarAerolinea(id);
        return ResponseEntity.noContent().build();
    }
}