package com.fiap.gerenciamento_clientes.infraestructure.implementations;

import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import com.fiap.gerenciamento_clientes.infraestructure.ClienteJpaRepository;
import com.fiap.gerenciamento_clientes.infraestructure.entities.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

@RequiredArgsConstructor
public class ClienteSqlRepositoryImpl implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;


    @Override
    public ClienteDatabaseDTO save(ClienteDatabaseDTO clienteDatabaseDTO) {
        try {
            ClienteEntity entity = ClienteEntity.builder()
                    .nome(clienteDatabaseDTO.getNome())
                    .email(clienteDatabaseDTO.getEmail())
                    .endereco(clienteDatabaseDTO.getEndereco())
                    .telefone(clienteDatabaseDTO.getTelefone())
                    .build();

            return clienteJpaRepository.save(entity).toDatabaseDTO();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id).map(ClienteEntity::toDomain);
    }
}
