package com.bank.microservice.services;

import com.bank.microservice.entities.Cliente;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.repositories.ClienteRepository;
import com.bank.microservice.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public Cuenta crearCuenta(Long clienteId, Cuenta cuenta){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }
}
