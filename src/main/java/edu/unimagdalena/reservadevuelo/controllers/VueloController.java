package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.entities.Vuelo;
import edu.unimagdalena.reservadevuelo.services.VueloService;
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

    @GetMapping
    public ResponseEntity<List<Vuelo>> getAllVuelos() {
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable Long id) {
        return vueloService.buscarVueloPorId(id)
                .map(v -> ResponseEntity.ok().body(v))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Vuelo> createVuelo(@RequestBody Vuelo vuelo) throws URISyntaxException {
        return createNewVuelo(vuelo);
    }

    private ResponseEntity<Vuelo> createNewVuelo(Vuelo vuelo) {
        Vuelo newVuelo = vueloService.guardarVuelo(vuelo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.getId())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long id, @RequestBody Vuelo vuelo) {
        Optional<Vuelo> vueloUdapte = vueloService.actualizarVuelo(id, vuelo);
        return vueloUdapte
                .map(v -> ResponseEntity.ok(v))
                .orElseGet(() -> {
                    return createNewVuelo(vuelo);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        vueloService.eliminarVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
