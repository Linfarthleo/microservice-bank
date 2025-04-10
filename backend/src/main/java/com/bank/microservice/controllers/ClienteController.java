package com.bank.microservice.controllers;

import com.bank.microservice.dtos.ClienteDTO;
import com.bank.microservice.entities.Cliente;
import com.bank.microservice.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos(){
        List<Cliente> clientes = clienteService.obtenerTodos();
        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }

    @GetMapping("/existe-username/{username}")
    public ResponseEntity<Boolean> verificarUsername(@PathVariable String username){
        boolean existe = clienteService.existeUsername(username);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.status(201).body(cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClientePorId(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
