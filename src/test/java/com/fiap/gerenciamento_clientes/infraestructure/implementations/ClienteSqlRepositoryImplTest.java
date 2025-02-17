package com.fiap.gerenciamento_clientes.infraestructure.implementations;

import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import com.fiap.gerenciamento_clientes.infraestructure.ClienteJpaRepository;
import com.fiap.gerenciamento_clientes.infraestructure.entities.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteSqlRepositoryImplTest {

    @InjectMocks
    private ClienteSqlRepositoryImpl clienteSqlRepository;

    @Mock
    private ClienteJpaRepository clienteJpaRepository;

    private ClienteDatabaseDTO clienteDatabaseDTO;
    private ClienteEntity clienteEntity;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDatabaseDTO = ClienteDatabaseDTO.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();

        clienteEntity = ClienteEntity.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();

        cliente = new Cliente(1L, "João", "joao@email.com", "999999999", "Rua A, 123");
    }

    @Test
    void testSalvarClienteComSucesso() {
        when(clienteJpaRepository.save(any())).thenReturn(clienteEntity);

        ClienteDatabaseDTO resultado = clienteSqlRepository.save(clienteDatabaseDTO);

        assertNotNull(resultado);
        assertEquals(clienteDatabaseDTO.getNome(), resultado.getNome());
        verify(clienteJpaRepository, times(1)).save(any());
    }

    @Test
    void testSalvarClienteComErro() {
        when(clienteJpaRepository.save(any())).thenThrow(new DataAccessException("Erro ao inserir") {});

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteSqlRepository.save(clienteDatabaseDTO));

        assertTrue(exception.getMessage().contains("Erro ao inserir cliente"));
        verify(clienteJpaRepository, times(1)).save(any());
    }

    @Test
    void testBuscarClientePorIdComSucesso() {
        when(clienteJpaRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        Optional<Cliente> resultado = clienteSqlRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(cliente.getId(), resultado.get().getId());
        verify(clienteJpaRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarClientePorIdNaoEncontrado() {
        when(clienteJpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = clienteSqlRepository.findById(1L);

        assertFalse(resultado.isPresent());
        verify(clienteJpaRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarClienteComSucesso() {
        when(clienteJpaRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));
        when(clienteJpaRepository.save(any())).thenReturn(clienteEntity);

        ClienteDatabaseDTO resultado = clienteSqlRepository.update(1L, clienteDatabaseDTO);

        assertNotNull(resultado);
        assertEquals(clienteDatabaseDTO.getNome(), resultado.getNome());
        verify(clienteJpaRepository, times(1)).findById(1L);
        verify(clienteJpaRepository, times(1)).save(any());
    }

    @Test
    void testAtualizarClienteNaoEncontrado() {
        when(clienteJpaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteSqlRepository.update(1L, clienteDatabaseDTO));

        assertTrue(exception.getMessage().contains("Cliente com ID 1 não encontrado."));
        verify(clienteJpaRepository, times(1)).findById(1L);
        verify(clienteJpaRepository, never()).save(any());
    }

    @Test
    void testAtualizarClienteComErroNoBanco() {
        when(clienteJpaRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));
        when(clienteJpaRepository.save(any())).thenThrow(new DataAccessException("Erro ao atualizar") {});

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteSqlRepository.update(1L, clienteDatabaseDTO));

        assertTrue(exception.getMessage().contains("Erro ao atualizar cliente com ID 1"));
        verify(clienteJpaRepository, times(1)).findById(1L);
        verify(clienteJpaRepository, times(1)).save(any());
    }

    @Test
    void testDeletarClienteComSucesso() {
        when(clienteJpaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteJpaRepository).deleteById(1L);

        assertDoesNotThrow(() -> clienteSqlRepository.delete(1L));

        verify(clienteJpaRepository, times(1)).existsById(1L);
        verify(clienteJpaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarClienteNaoEncontrado() {
        when(clienteJpaRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteSqlRepository.delete(1L));

        assertTrue(exception.getMessage().contains("Cliente com ID 1 não encontrado."));
        verify(clienteJpaRepository, times(1)).existsById(1L);
        verify(clienteJpaRepository, never()).deleteById(1L);
    }

    @Test
    void testDeletarClienteComErroNoBanco() {
        when(clienteJpaRepository.existsById(1L)).thenReturn(true);
        doThrow(new DataAccessException("Erro ao deletar") {}).when(clienteJpaRepository).deleteById(1L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteSqlRepository.delete(1L));

        assertTrue(exception.getMessage().contains("Erro ao deletar cliente com ID 1"));
        verify(clienteJpaRepository, times(1)).existsById(1L);
        verify(clienteJpaRepository, times(1)).deleteById(1L);
    }
}
