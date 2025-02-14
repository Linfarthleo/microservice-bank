package com.bank.microservice.services;

import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.entities.Movimiento;
import com.bank.microservice.entities.TipoMovimiento;
import com.bank.microservice.repositories.CuentaRepository;
import com.bank.microservice.repositories.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public Movimiento realizarMovimiento(Long cuentaId, Movimiento movimiento){
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(()->new RuntimeException("Cuenta no encontrada"));
        if (movimiento.getTipoMovimiento() == TipoMovimiento.RETIRO && movimiento.getValor() > cuenta.getSaldoInicial()){
            throw new RuntimeException("Saldo no disponible");
        }
        
        double nuevoSaldo = movimiento.getTipoMovimiento() == TipoMovimiento.DEPOSITO
                ? cuenta.getSaldoInicial() + movimiento.getValor()
                : cuenta.getSaldoInicial() - movimiento.getValor();
        
        movimiento.setSaldoDisponible(nuevoSaldo);
        cuenta.setSaldoInicial(nuevoSaldo);
        
        cuentaRepository.save(cuenta);
        S save = movimientoRepository.save(movimiento);
        return save;
    }
}
