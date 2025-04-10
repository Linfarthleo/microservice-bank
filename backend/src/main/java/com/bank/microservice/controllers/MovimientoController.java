package com.bank.microservice.controllers;

import com.bank.microservice.dtos.MovimientoDTO;
import com.bank.microservice.dtos.ReporteDTO;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.entities.Movimiento;
import com.bank.microservice.services.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    private final MovimientoService movimientoService;

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<ReporteDTO>> obtenerMovimientosPorClienteIdYFechas(@PathVariable Long cuentaId,
                                                                                  @RequestParam LocalDateTime inicio,
                                                                                  @RequestParam LocalDateTime fin) {
        List<ReporteDTO> movimientos = movimientoService.obtenerMovimientosPorClienteIdYFechas(cuentaId,inicio,fin);
        return movimientos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movimientos);
    }

    @PostMapping
    public ResponseEntity<Movimiento> realizarMovimiento(@Valid @RequestBody MovimientoDTO movimientoDTO){
        Movimiento nuevoMovimiento = movimientoService.realizarMovimiento(movimientoDTO);
        return ResponseEntity.status(201).body(nuevoMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id){
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
