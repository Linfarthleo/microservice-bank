package com.bank.microservice;

import com.bank.microservice.entities.Cliente;
import com.bank.microservice.entities.Cuenta;
import com.bank.microservice.repositories.ClienteRepository;
import com.bank.microservice.repositories.CuentaRepository;
import com.bank.microservice.services.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CuentaService cuentaService;

    private Cliente cliente;
    private Cuenta cuenta;

    @BeforeEach
    void setUp(){
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Pérez");

        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("1234567891");
        cuenta.setCliente(cliente);
    }

    @Test
    void obtenerPorCliente_DeberiaRetornarListaDeCuentas(){
        when(cuentaRepository.findByClienteId(1L)).thenReturn(List.of(cuenta));

        List<Cuenta> cuentas = cuentaService.obtenerPorClienteId(1L);

        assertEquals(1, cuentas.size());
        verify(cuentaRepository, times(1)).findByClienteId(1L);
    }

    @Test
    void obtenerPorNumero_DeberiaRetornarCuenta(){
        when(cuentaRepository.findByNumeroCuenta("1234567891")).thenReturn(Optional.of(cuenta));

        Cuenta resultado = cuentaService.obtenerPorNumeroDeCuenta("1234567891");

        assertNotNull(resultado);
        assertEquals("1234567891", cuenta.getNumeroCuenta());
        verify(cuentaRepository, times(1)).findByNumeroCuenta("1234567891");
    }

    @Test
    void obtenerPorNumero_CuentaNoExistente_DeberiaLanzarExcepcion(){

        when(cuentaRepository.findByNumeroCuenta("99999999")).thenReturn(Optional.empty());


        Exception exception = assertThrows(RuntimeException.class, () -> {
            cuentaService.obtenerPorNumeroDeCuenta("99999999");
        });

        assertEquals("Cuenta no encontrada", exception.getMessage());
    }
}
