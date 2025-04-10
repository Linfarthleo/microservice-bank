package com.bank.microservice.controllers;

import com.bank.microservice.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/reportes")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;

    @GetMapping("/json")
    public ResponseEntity<?> generarReporteJson(@RequestParam Long clienteId,
                                                @RequestParam LocalDateTime inicio,
                                                @RequestParam LocalDateTime fin){
        return ResponseEntity.ok(reporteService.generarReporteJson(clienteId,inicio,fin));
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarReportePDF(@RequestParam Long clienteId,
                                                    @RequestParam String inicio,
                                                    @RequestParam String fin){
        LocalDateTime inicioDateTime = LocalDate.parse(inicio).atStartOfDay();
        LocalDateTime finDateTime = LocalDate.parse(fin).atTime(23, 59, 59);
        return reporteService.generarReportePdf(clienteId, inicioDateTime, finDateTime);
    }

}
