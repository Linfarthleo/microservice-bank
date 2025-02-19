package com.bank.microservice.services;

import com.bank.microservice.dtos.MovimientoDTO;
import com.bank.microservice.dtos.ReporteDTO;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.entities.Movimiento;
import com.bank.microservice.entities.TipoMovimiento;
import com.bank.microservice.exceptions.SaldoInsuficienteException;
import com.bank.microservice.repositories.CuentaRepository;
import com.bank.microservice.repositories.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public List<ReporteDTO> obtenerMovimientosPorClienteIdYFechas(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        List<Movimiento> movimientos = movimientoRepository.findByCuenta_Cliente_IdAndFechaBetween(clienteId,fechaInicio,fechaFin);

        return movimientos.stream().map(movimiento -> {
            ReporteDTO reporte = new ReporteDTO();
            reporte.setFechaMovimiento(movimiento.getFecha());
            reporte.setNombreCliente(movimiento.getCuenta().getCliente().getNombre());
            reporte.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
            reporte.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
            reporte.setTipoMovimiento(movimiento.getTipoMovimiento());
            reporte.setSaldoInicial(movimiento.getSaldoInicial());
            reporte.setEstado(movimiento.getCuenta().isEstado());
            reporte.setValorMovimiento(movimiento.getValor());
            reporte.setSaldoDisponible(movimiento.getSaldoDisponible());
            return reporte;
        }).collect(Collectors.toList());
    }

    public Movimiento realizarMovimiento(MovimientoDTO movimientoDTO){

        // 1. Buscar la cuenta asociada al ID recibido
        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuentaId())
                .orElseThrow(()->new RuntimeException("Cuenta no encontrada"));


        // 2. Validar si hay saldo disponible en caso de RETIRO y en caso de DEPOSITO sumar el valor

        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal valorMovimiento = movimientoDTO.getValor();

        if (movimientoDTO.getTipoMovimiento() == TipoMovimiento.RETIRO){
            if (saldoActual.compareTo(valorMovimiento) < 0){
                throw new SaldoInsuficienteException("Saldo insuficiente para realizar retiro");
            }
            saldoActual = saldoActual.subtract(valorMovimiento);
            valorMovimiento = valorMovimiento.negate();
        }else if (movimientoDTO.getTipoMovimiento() == TipoMovimiento.DEPOSITO){
            saldoActual = saldoActual.add(valorMovimiento);
        }

        //4. Crear objeto movimiento y seteo de valores
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setSaldoInicial(cuenta.getSaldoInicial());
        movimiento.setValor(valorMovimiento);//Negativo si es retiro
        movimiento.setSaldoDisponible(saldoActual);
        movimiento.setCuenta(cuenta);

        //5. Actualizar el saldo de la cuenta en la base de datos
        cuenta.setSaldoInicial(saldoActual);
        cuentaRepository.save(cuenta);

        //6. Guardar movimiento en la base de datos
        return movimientoRepository.save(movimiento);
    }

    public void eliminarMovimiento(Long id){
        movimientoRepository.deleteById(id);
    }
}
