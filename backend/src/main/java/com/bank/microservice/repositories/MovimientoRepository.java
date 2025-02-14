package com.bank.microservice.repositories;

import com.bank.microservice.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoRepository, Long> {
    List<MovimientoRepository> findByCuentaAndFechaBetween(Cuenta cuenta, String fechaInicio, String fechaFin);
}
