package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.DeleteClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteClienteUseCase implements DeleteClienteUseCasePorts {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public void deletar(Long id) {
        if (!clienteRepositoryPort.findById(id).isPresent()) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepositoryPort.delete(id);
    }

}
