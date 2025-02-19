package com.bank.microservice.controllers;

import com.bank.microservice.dtos.CuentaDTO;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.services.CuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaService cuentaService;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cuenta>> obtenerPorClienteId(@PathVariable Long clienteId){
        List<Cuenta> cuentas = cuentaService.obtenerPorClienteId(clienteId);
        return cuentas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerPorNumeroDeCuenta(@PathVariable String numeroCuenta){
        Cuenta cuenta = cuentaService.obtenerPorNumeroDeCuenta(numeroCuenta);
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody CuentaDTO cuentaDTO){
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuentaDTO);
        return ResponseEntity.status(201).body(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @Valid @RequestBody CuentaDTO cuentaDTO){
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuentaDTO);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaPorId(@PathVariable Long id){
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}