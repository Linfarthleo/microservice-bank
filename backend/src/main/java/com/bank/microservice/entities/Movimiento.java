package com.bank.microservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    private double valor;
    private double saldoDisponible;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

}
