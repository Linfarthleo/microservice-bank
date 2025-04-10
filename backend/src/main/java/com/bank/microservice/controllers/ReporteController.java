package com.bank.microservice.controllers;

import com.bank.microservice.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;

    @GetMapping("/api/json")
    public ResponseEntity<?> generarReporteJson(@RequestParam Long clienteId,
                                                @RequestParam LocalDateTime inicio,
                                                @RequestParam LocalDateTime fin){
        return ResponseEntity.ok(reporteService.generarReporteJson(clienteId,inicio,fin));
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarReportePDF(@RequestParam Long clienteId,
                                                @RequestParam LocalDateTime inicio,
                                                @RequestParam LocalDateTime fin){
        return reporteService.generarReportePdf(clienteId,inicio,fin);
    }
}
