package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.AeropuertoDto;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.aeropuerto.AeropuertoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuerto")
@RequiredArgsConstructor
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    @GetMapping("")
    public ResponseEntity<List<AeropuertoDto>> getAllAeropuertos() {
        List<AeropuertoDto> aeropuertosDto = aeropuertoService.buscarAeropuertos();
        if (aeropuertosDto.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron aeropurtos registrados");
        }
        return ResponseEntity.ok(aeropuertosDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AeropuertoDto> getAeropuertoById(@PathVariable Long id){
        AeropuertoDto aeropuertoDto = aeropuertoService.buscarAeropuertoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aeropuerto con ID " + id + " no encontrado."));
        return ResponseEntity.ok(aeropuertoDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<AeropuertoDto>> getAeropuertosByNombre(@PathVariable String name){
        List<AeropuertoDto> aeropuertos = aeropuertoService.buscarAeropuertosPorNombre(name);
        if (aeropuertos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron aeropuertos con el nombre: " + name);
        }
        return ResponseEntity.ok(aeropuertos);
    }

    @GetMapping("/IATA/{IATA}")
    public ResponseEntity<AeropuertoDto> getAeropuertoByIATA(@PathVariable String IATA){
        AeropuertoDto aeropuertoDto = aeropuertoService.buscarAeropuertoPorCodigoIATA(IATA)
                .orElseThrow(() -> new ResourceNotFoundException("Aeropuerto con código IATA '" + IATA + "' no encontrado."));
        return ResponseEntity.ok(aeropuertoDto);
    }

    @PostMapping("")
    public ResponseEntity<AeropuertoDto> createAeropuerto(@RequestBody AeropuertoDto aeropuertoDto) throws URISyntaxException {
        if(aeropuertoDto.codigoIATA() == null)
            return ResponseEntity.badRequest().build();
        return createNewAeropuerto(aeropuertoDto);
    }

    public ResponseEntity<AeropuertoDto> createNewAeropuerto(AeropuertoDto aeropuertoDto){
        AeropuertoDto newAeropuertoDto = aeropuertoService.guardarAeropuerto(aeropuertoDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(newAeropuertoDto.id()).
                toUri();
        return ResponseEntity.created(location).body(newAeropuertoDto);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<AeropuertoDto> updateAeropuerto(@PathVariable Long id, @RequestBody AeropuertoDto aeropuertoDto) {
        Optional<AeropuertoDto> aeropuertoUpdate = aeropuertoService.actualizarAeropuerto(id, aeropuertoDto);
        return aeropuertoUpdate
                .map(aeropuerto -> ResponseEntity.ok()
                        .body(aeropuerto))
                .orElseThrow(() -> new ResourceNotFoundException("Aeropuerto con ID " + id + " no encontrado."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id){
        boolean exists = aeropuertoService.existeAeropuertoPorId(id);
        if(!exists) {
            throw new ResourceNotFoundException("Aeropuerto con ID " + id + " no existe.");
        }
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }
}
