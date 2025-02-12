package com.fiap.gerenciamento_clientes.domain.ports;

import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;

import java.util.Optional;

public interface ClienteRepositoryPort {
    ClienteDatabaseDTO save(ClienteDatabaseDTO clienteDatabaseDTO);
    Optional<Cliente> findById(Long id);
    ClienteDatabaseDTO update(Long id, ClienteDatabaseDTO clienteDatabaseDTO);
    void delete(Long id);
}
