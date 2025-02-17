package com.fiap.gerenciamento_clientes.application.controller;

import com.fiap.gerenciamento_clientes.application.controller.ClienteController;
import com.fiap.gerenciamento_clientes.application.controller.dto.input.ClienteInput;
import com.fiap.gerenciamento_clientes.application.controller.dto.output.ClienteOutput;
import com.fiap.gerenciamento_clientes.application.ports.AtualizarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.BuscarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.CadastrarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.usecase.DeleteClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private CadastrarClienteUseCasePorts cadastrarClienteUseCasePorts;

    @Mock
    private BuscarClienteUseCasePorts buscarClienteUseCasePorts;

    @Mock
    private AtualizarClienteUseCasePorts atualizarClienteUseCasePorts;

    @Mock
    private DeleteClienteUseCase deleteClienteUseCase;

    private ClienteInput clienteInput;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        clienteInput = new ClienteInput();
        clienteDTO = ClienteDTO.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .telefone("999999999")
                .endereco("Rua A, 123")
                .build();
    }

    @Test
    void testCadastrar() {
        when(cadastrarClienteUseCasePorts.cadastrar(any())).thenReturn(clienteDTO);

        ResponseEntity<ClienteOutput> response = clienteController.cadastrar(clienteInput);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("João", response.getBody().getNome());
        verify(cadastrarClienteUseCasePorts, times(1)).cadastrar(any());
    }

    @Test
    void testVisualizarCliente() {
        when(buscarClienteUseCasePorts.buscarCliente(anyLong())).thenReturn(clienteDTO);

        ResponseEntity<ClienteOutput> response = clienteController.visualizarCliente(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("João", response.getBody().getNome());
        verify(buscarClienteUseCasePorts, times(1)).buscarCliente(anyLong());
    }

    @Test
    void testAtualizarCliente() {
        when(atualizarClienteUseCasePorts.atualizarCliente(anyLong(), any())).thenReturn(clienteDTO);

        ResponseEntity<ClienteOutput> response = clienteController.atualizarCliente(1L, clienteInput);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("João", response.getBody().getNome());
        verify(atualizarClienteUseCasePorts, times(1)).atualizarCliente(anyLong(), any());
    }

    @Test
    void testExcluirCliente() {
        doNothing().when(deleteClienteUseCase).deletar(anyLong());

        ResponseEntity<Void> response = clienteController.excluirCliente(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(deleteClienteUseCase, times(1)).deletar(anyLong());
    }
}