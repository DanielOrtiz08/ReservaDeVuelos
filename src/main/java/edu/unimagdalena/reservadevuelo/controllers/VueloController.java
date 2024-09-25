package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.Entities.Vuelo;
import edu.unimagdalena.reservadevuelo.services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
