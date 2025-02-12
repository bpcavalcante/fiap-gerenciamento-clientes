package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.AtualizarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarClienteUseCase implements AtualizarClienteUseCasePorts {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
       clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

       ClienteDatabaseDTO clienteDatabaseDTO = clienteDTO.toDatabaseDTO();

        ClienteDatabaseDTO updatedCliente = clienteRepositoryPort.update(id, clienteDatabaseDTO);

        return updatedCliente.toDTO();
    }
}
