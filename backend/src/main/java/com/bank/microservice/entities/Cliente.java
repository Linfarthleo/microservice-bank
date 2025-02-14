package com.bank.microservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

    @Column(unique = true, nullable = false)
    private String clienteId;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private boolean estado;
}
