package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClienteUseCaseTest {

    @InjectMocks
    private BuscarClienteUseCase buscarClienteUseCase;

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = Cliente.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();

        clienteDTO = cliente.toDTO().toDTO();

    }

    @Test
    void testBuscarClienteComSucesso() {
        when(clienteRepositoryPort.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteDTO resultado = buscarClienteUseCase.buscarCliente(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(clienteRepositoryPort, times(1)).findById(1L);
    }

    @Test
    void testBuscarClienteNaoEncontrado() {
        when(clienteRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> buscarClienteUseCase.buscarCliente(1L));
        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteRepositoryPort, times(1)).findById(1L);
    }
}
