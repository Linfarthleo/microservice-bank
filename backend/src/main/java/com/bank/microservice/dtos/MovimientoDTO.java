package com.bank.microservice.dtos;

import com.bank.microservice.entities.TipoMovimiento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class MovimientoDTO {
    private LocalDateTime fecha = LocalDateTime.now();

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "El valor del movimiento es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El valor del movimiento debe ser mayor a 0")
    private BigDecimal valor;

    @NotNull(message = "El ID de la cuenta debe ser obligatorio")
    private Long cuentaId;
}
