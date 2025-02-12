package com.fiap.gerenciamento_clientes.application.controller.dto.input;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInput {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteDTO toDTO() {
        return ClienteDTO.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

}
