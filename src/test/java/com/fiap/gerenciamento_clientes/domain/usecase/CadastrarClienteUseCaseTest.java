package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseTest {

    @InjectMocks
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private ClienteDTO clienteDTO;
    private ClienteDatabaseDTO clienteDatabaseDTO;

    @BeforeEach
    void setUp() {
        clienteDTO = ClienteDTO.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();

        clienteDatabaseDTO = ClienteDatabaseDTO.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();
    }

    @Test
    void testCadastrarClienteComSucesso() {
        when(clienteRepositoryPort.save(any())).thenReturn(clienteDatabaseDTO);

        ClienteDTO resultado = cadastrarClienteUseCase.cadastrar(clienteDTO);

        assertNotNull(resultado);
        assertEquals(clienteDTO.getId(), resultado.getId());
        assertEquals(clienteDTO.getNome(), resultado.getNome());
        verify(clienteRepositoryPort, times(1)).save(any());
    }

    @Test
    void testCadastrarClienteComErroNoSalvar() {
        when(clienteRepositoryPort.save(any())).thenThrow(new RuntimeException("Erro ao salvar no repositório"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastrarClienteUseCase.cadastrar(clienteDTO);
        });

        assertEquals("Erro ao salvar no repositório", exception.getMessage());
        verify(clienteRepositoryPort, times(1)).save(any());
    }

    @Test
    void testCadastrarClienteComClienteDTOInvalido() {
        assertThrows(NullPointerException.class, () -> cadastrarClienteUseCase.cadastrar(null));
    }

    @Test
    void testCadastrarClienteComDadosDiferentesNoSave() {
        ClienteDatabaseDTO clienteDatabaseDTOAlterado = ClienteDatabaseDTO.builder()
                .id(2L) // id diferente para testar
                .nome("João Alterado")
                .email("alterado@email.com")
                .telefone("888888888")
                .endereco("Rua B, 456")
                .build();

        when(clienteRepositoryPort.save(any())).thenReturn(clienteDatabaseDTOAlterado);

        ClienteDTO resultado = cadastrarClienteUseCase.cadastrar(clienteDTO);

        assertNotNull(resultado);
        assertEquals(clienteDatabaseDTOAlterado.getId(), resultado.getId());
        assertEquals(clienteDatabaseDTOAlterado.getNome(), resultado.getNome());
        assertNotEquals(clienteDTO.getNome(), resultado.getNome());
        assertNotEquals(clienteDTO.getEmail(), resultado.getEmail());
        assertNotEquals(clienteDTO.getTelefone(), resultado.getTelefone());
        assertNotEquals(clienteDTO.getEndereco(), resultado.getEndereco());

        verify(clienteRepositoryPort, times(1)).save(any());
    }

}


