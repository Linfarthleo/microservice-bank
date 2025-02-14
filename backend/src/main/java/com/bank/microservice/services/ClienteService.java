package com.bank.microservice.services;

import com.bank.microservice.entities.Cliente;
import com.bank.microservice.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
