package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.Entities.Vuelo;
import edu.unimagdalena.reservadevuelo.services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping
    public ResponseEntity<List<Vuelo>> allVuelos() {
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> vueloById(Long id) {
        return vueloService.buscarVueloPorId(id)
                .map(v -> ResponseEntity.ok().body(v))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vuelo> createVuelo(Vuelo vuelo) {
        return ResponseEntity.ok(vueloService.guardarVuelo(vuelo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> updateVuelo(Long id, Vuelo vuelo) {
        return vueloService.buscarVueloPorId(id)
                .map(v -> ResponseEntity.ok(vueloService.guardarVuelo(vuelo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVuelo(Long id) {
        vueloService.eliminarVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
