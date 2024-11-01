package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.VueloDto;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.vuelo.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("")
    public ResponseEntity<List<VueloDto>> getAllVuelos() {
        List<VueloDto> vuelos = vueloService.buscarVuelos();
        if (vuelos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron vuelos registrados.");
        }
        return ResponseEntity.ok(vuelos);    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VueloDto> getVueloById(@PathVariable Long id) {
        return vueloService.buscarVueloPorId(id)
                .map(v -> ResponseEntity.ok().body(v))
                .orElseThrow(() -> new ResourceNotFoundException("Vuelo con ID " + id + " no encontrado."));
    }

    @PostMapping("")
    public ResponseEntity<VueloDto> createVuelo(@RequestBody VueloDto vueloDto) throws URISyntaxException {
        return createNewVuelo(vueloDto);
    }

    private ResponseEntity<VueloDto> createNewVuelo(VueloDto vueloDto) {
        VueloDto newVuelo = vueloService.guardarVuelo(vueloDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.id())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<VueloDto> updateVuelo(@PathVariable Long id, @RequestBody VueloDto vueloDto) {
        Optional<VueloDto> vueloUpdated = vueloService.buscarVueloPorId(id);
        if (vueloUpdated.isEmpty()) {
            throw new ResourceNotFoundException("Vuelo con ID " + id + " no encontrado para actualizar.");
        }
        VueloDto updatedVuelo = vueloService.actualizarVuelo(id, vueloDto)
                .orElseThrow(() -> new ResourceNotFoundException("Error actualizando el vuelo con ID " + id));
        return ResponseEntity.ok(updatedVuelo);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        if (!vueloService.existeVueloPorId(id)) {
            throw new ResourceNotFoundException("Vuelo con ID " + id + " no encontrado para eliminar.");
        }
        vueloService.eliminarVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
