package com.fiap.gerenciamento_clientes.infraestructure.entities;

import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CLIENTE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteDatabaseDTO toDatabaseDTO() {
        return ClienteDatabaseDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

    public Cliente toDomain() {
        return Cliente.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }
}
