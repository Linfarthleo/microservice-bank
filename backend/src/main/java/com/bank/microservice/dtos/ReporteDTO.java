package com.bank.microservice.dtos;

import com.bank.microservice.entities.TipoCuenta;
import com.bank.microservice.entities.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {
    private LocalDateTime fechaMovimiento;
    private String nombreCliente;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal valorMovimiento;
    private BigDecimal saldoDisponible;


}
