package com.bank.microservice.services;

import com.bank.microservice.dtos.ClienteDTO;
import com.bank.microservice.entities.Cliente;
import com.bank.microservice.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Cliente> obtenerTodos(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente crearCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setUsername(clienteDTO.getUsername());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEstado(clienteDTO.isEstado());
        cliente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));

        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = obtenerClientePorId(id);
        //Actulizar solo los campos permitidos
        clienteExistente.setNombre(clienteDTO.getNombre());
        clienteExistente.setGenero(clienteDTO.getGenero());
        clienteExistente.setEdad(clienteDTO.getEdad());
        clienteExistente.setDireccion(clienteDTO.getDireccion());
        clienteExistente.setTelefono(clienteDTO.getTelefono());
        clienteExistente.setUsername(clienteDTO.getUsername());
        clienteExistente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
        clienteExistente.setEstado(clienteDTO.isEstado());

        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public boolean existeUsername(String username) {
        return clienteRepository.existsByUsername(username);
    }
}
