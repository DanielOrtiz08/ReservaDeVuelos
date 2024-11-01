package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.PasajeroDto;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.pasajero.PasajeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/pasajero")
@RestController
@RequiredArgsConstructor
public class PasajeroController {
    private final PasajeroService pasajeroService;

    @GetMapping("")
    public ResponseEntity<List<PasajeroDto>> getAllPasajeros(){
        List<PasajeroDto> pasajeros = pasajeroService.buscarPasajeros();
        if (pasajeros.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pasajeros registrados.");
        }
        return ResponseEntity.ok(pasajeros);    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PasajeroDto> getPasajeroById(@PathVariable Long id){
        return pasajeroService.buscarPasajeroPorId(id)
                .map(pasajeroDto -> ResponseEntity.ok().body(pasajeroDto))
                .orElseThrow(() -> new ResourceNotFoundException("Pasajero con ID " + id + " no encontrado."));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PasajeroDto>> getPasajeroByNombre(@PathVariable String name){
        List<PasajeroDto> pasajerosDto = pasajeroService.buscarPasajerosPorNombre(name);
        if (pasajerosDto.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pasajeros con el nombre: " + name);
        }
        return ResponseEntity.ok(pasajerosDto);
    }

    @PostMapping("")
    public ResponseEntity<PasajeroDto> createPasajero(@RequestBody PasajeroDto pasajeroDto) throws URISyntaxException {
        return createNewPasajero(pasajeroDto);
    }

    private ResponseEntity<PasajeroDto> createNewPasajero(PasajeroDto pasajeroDto){
        PasajeroDto newPasajero = pasajeroService.guardarPasajero(pasajeroDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPasajero.id())
                .toUri();
        return ResponseEntity.created(location).body(newPasajero);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<PasajeroDto> updatePasajero(@PathVariable Long id, @RequestBody PasajeroDto pasajeroDto){
        Optional<PasajeroDto> pasajeroUpdated = pasajeroService.actualizarPasajero(id, pasajeroDto);
        return pasajeroUpdated
                .map(p -> ResponseEntity.ok(p))
                .orElseThrow(() -> new ResourceNotFoundException("Pasajero con ID " + id + " no encontrado para actualizar."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<PasajeroDto> deletePasajero(@PathVariable Long id){
        if (!pasajeroService.existePasajeroPorId(id)) {
            throw new ResourceNotFoundException("Pasajero con ID " + id + " no encontrado para eliminar.");
        }
        pasajeroService.eliminarPasajero(id);
        return ResponseEntity.noContent().build();
    }
}
