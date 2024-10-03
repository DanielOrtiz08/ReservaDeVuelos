package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import edu.unimagdalena.reservadevuelo.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@ResponseBody
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.buscarClientes());
        //return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientes());
    }

    @GetMapping("/id")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws URISyntaxException {
        return createNewCliente(cliente);
        //return ResponseEntity.created(new URI("/api/v1/clientes/" + newCliente.getId())).body(newCliente);
    }

    private ResponseEntity<Cliente> createNewCliente(Cliente cliente) {
        clienteService.guardarCliente(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(cliente).
                toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping("/id")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteUpdate = clienteService.actualizarCliente(id, cliente);
        return clienteUpdate
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewCliente(cliente);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
