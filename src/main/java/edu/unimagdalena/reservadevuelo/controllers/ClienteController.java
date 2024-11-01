package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.ClienteDto;
import edu.unimagdalena.reservadevuelo.exeptions.ClientNotFoundException;
import edu.unimagdalena.reservadevuelo.exeptions.DuplicateResourceException;
import edu.unimagdalena.reservadevuelo.exeptions.ResourceNotFoundException;
import edu.unimagdalena.reservadevuelo.services.cliente.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/clientes")
@RestController
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientesDto = clienteService.buscarClientes();
        if (clientesDto.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron clientes registrados.");
        }
        return ResponseEntity.ok(clientesDto);
        //return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientes());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(clienteDto -> ResponseEntity.ok().body(clienteDto))
                //.orElseThrow(ClientNotFoundException::new);
               .orElseThrow(() -> new ClientNotFoundException("No se encontro el cliente con el id: " + id));
    }

    @PostMapping("")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto cliente) throws URISyntaxException {
        if (clienteService.existeClienteConCorreoElectronico(cliente.correoElectronico())) {
            throw new DuplicateResourceException("El correo electronico '" + cliente.correoElectronico() + "' ya está registrado.");
        }
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

    @PutMapping("/id/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) throws URISyntaxException {
        Optional<ClienteDto> clienteUpdate = clienteService.actualizarCliente(id, clienteDto);
        return clienteUpdate
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no se encontró para actualizar."));

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteService.existeClientePorId(id)) {
            throw new ResourceNotFoundException("Cliente con ID " + id + " no encontrado para eliminar.");
        }
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
