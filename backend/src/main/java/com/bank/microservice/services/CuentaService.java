package com.bank.microservice.services;

import com.bank.microservice.dtos.CuentaDTO;
import com.bank.microservice.entities.Cliente;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.repositories.ClienteRepository;
import com.bank.microservice.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public List<Cuenta> obtenerTodos(){
        return cuentaRepository.findAll();
    }

    public List<Cuenta> obtenerPorClienteId(Long clienteId){
        return cuentaRepository.findByClienteId(clienteId);
    }

    public Cuenta obtenerPorNumeroDeCuenta(String numeroCuenta){
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(()-> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta crearCuenta(CuentaDTO cuentaDTO){
        Cliente cliente = clienteRepository.findById(cuentaDTO.getClienteId())
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.isEstado());
        cuenta.setCliente(cliente);

        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Long id, CuentaDTO cuentaDTO){
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));

        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.isEstado());

        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id){
        cuentaRepository.deleteById(id);
    }
}
