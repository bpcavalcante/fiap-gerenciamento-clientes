package com.fiap.gerenciamento_clientes.domain.usecase;

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
class DeleteClienteUseCaseTest {

    @InjectMocks
    private DeleteClienteUseCase deleteClienteUseCase;

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1L, "João", "joao@email.com", "999999999", "Rua A, 123");
    }

    @Test
    void testDeletarClienteComSucesso() {
        Long id = 1L;
        when(clienteRepositoryPort.findById(id)).thenReturn(Optional.of(cliente));

        doNothing().when(clienteRepositoryPort).delete(id);

        deleteClienteUseCase.deletar(id);

        verify(clienteRepositoryPort, times(1)).delete(id);
    }

    @Test
    void testDeletarClienteNaoEncontrado() {
        Long id = 1L;
        when(clienteRepositoryPort.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            deleteClienteUseCase.deletar(id);
        });

        assertEquals("Cliente com ID 1 não encontrado.", exception.getMessage());
        verify(clienteRepositoryPort, never()).delete(id);
    }

}
