package com.bank.microservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    private double saldoInicial;
    private boolean estado;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
