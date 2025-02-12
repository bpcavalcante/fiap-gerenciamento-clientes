package com.fiap.gerenciamento_clientes.infraestructure;

import com.fiap.gerenciamento_clientes.infraestructure.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {
}
