package com.fiap.gerenciamento_clientes.application.controller.dto.output;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteOutput {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
}
