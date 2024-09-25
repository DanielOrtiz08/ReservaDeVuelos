package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.Entities.Aeropuerto;
import edu.unimagdalena.reservadevuelo.services.AeropuertoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aeropuerto")
public class AeropuertoController {
    private AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService){
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping
    public ResponseEntity<List<Aeropuerto>> allAeropuertos(){
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aeropuerto> aeropuertoById(Long id){
        return aeropuertoService.buscarAeropuertoPorId(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Aeropuerto>> aeropuertoByNombre(String nombre){
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertosPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Aeropuerto> saveAeropuerto(@RequestBody Aeropuerto aeropuerto){
        Aeropuerto aeropuertoGuardado = aeropuertoService.guardarAeropuerto(aeropuerto);
        return ResponseEntity.ok(aeropuertoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aeropuerto> updateAeropuerto(@PathVariable Long id, @RequestBody Aeropuerto aeropuerto){
        return aeropuertoService.actualizarAeropuerto(id, aeropuerto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id){
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }
}
