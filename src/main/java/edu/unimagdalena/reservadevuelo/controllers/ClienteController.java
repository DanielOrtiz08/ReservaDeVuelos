package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.entities.Cliente;
import edu.unimagdalena.reservadevuelo.exeptions.ClientNotFoundException;
import edu.unimagdalena.reservadevuelo.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/clientes")
@RestController
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        return ResponseEntity.ok(clienteService.buscarClientes());
        //return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(c -> ResponseEntity.ok().body(c))
                //.orElseThrow(ClientNotFoundException::new);
               .orElseThrow(() -> new ClientNotFoundException("No se encontro el cliente con el id: " + id));
    }

    @PostMapping()
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto cliente) throws URISyntaxException {
        return createNewCliente(cliente);
        //return ResponseEntity.created(new URI("/api/v1/clientes/" + newCliente.getId())).body(newCliente);
    }

    private ResponseEntity<ClienteDto> createNewCliente(ClienteDto cliente) {
        ClienteDto newCliente = clienteService.guardarCliente(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(newCliente.id()).
                toUri();
        return ResponseEntity.created(location).body(newCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) throws URISyntaxException {
        Optional<ClienteDto> clienteUpdate = clienteService.actualizarCliente(id, clienteDto);
        return clienteUpdate
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewCliente(clienteDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
