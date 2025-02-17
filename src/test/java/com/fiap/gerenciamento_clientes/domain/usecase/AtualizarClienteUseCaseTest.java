package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.Cliente;
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
class AtualizarClienteUseCaseTest {

    @InjectMocks
    private AtualizarClienteUseCase atualizarClienteUseCase;

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

        clienteDTO = ClienteDTO.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();
    }

    @Test
    void testAtualizarCliente_ComSucesso() {
        when(clienteRepositoryPort.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepositoryPort.update(eq(1L), any())).thenReturn(cliente.toDTO());

        ClienteDTO resultado = atualizarClienteUseCase.atualizarCliente(1L, clienteDTO);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(clienteRepositoryPort, times(1)).findById(1L);
        verify(clienteRepositoryPort, times(1)).update(eq(1L), any());
    }

    @Test
    void testAtualizarCliente_ClienteNaoEncontrado() {
        when(clienteRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            atualizarClienteUseCase.atualizarCliente(1L, clienteDTO);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteRepositoryPort, times(1)).findById(1L);
        verify(clienteRepositoryPort, never()).update(anyLong(), any());
    }
}