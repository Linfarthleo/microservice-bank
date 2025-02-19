package com.bank.microservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bank.microservice.dtos.ClienteDTO;
import com.bank.microservice.entities.Cliente;
import com.bank.microservice.repositories.ClienteRepository;
import com.bank.microservice.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp(){
        cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNombre("Pedro Morales");

        cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Maria Benalcazar");
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDeClientes(){

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.obtenerTodos();

        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_ClienteExistente_DeberiaRetornarCliente(){

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));

        Cliente resultado = clienteService.obtenerClientePorId(1L);

        assertNotNull(resultado);
        assertEquals("Pedro Morales", resultado.getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerPorId_ClienteNoExistente_DeberiaLanzarExcepcion(){

        when(clienteRepository.findById(3L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
                    clienteService.obtenerClientePorId(3L);
                });

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    void crearCliente_DeberiaGuardarCliente(){

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Pedro Morales");
        clienteDTO.setPassword("Pedrito123");
        clienteDTO.setEdad(35);

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente1);

        Cliente clienteCreado = clienteService.crearCliente(clienteDTO);

        assertNotNull(clienteCreado);
        assertEquals("Pedro Morales", clienteCreado.getNombre());

        verify(passwordEncoder, times(1)).encode("Pedrito123");
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

}
