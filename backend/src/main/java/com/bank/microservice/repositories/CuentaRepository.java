package com.bank.microservice.repositories;

import com.bank.microservice.entities.Cliente;
import com.bank.microservice.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    List<Cuenta> findByCliente(Cliente cliente);
}
