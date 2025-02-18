# Tech Challenge Sistema de Gerenciamento de Pedidos

## Visão Geral

Descrição funcional: este serviço será responsável por todas as 
operações relacionadas aos clientes, incluindo a criação, leitura, atualização e 
exclusão de registros de clientes (CRUD).

Tecnologias e abordagens:
 - Java 21
 - Spring Boot
 - Spring Data JPA
 - Arquitetura Clean Architecture
 - Banco de Dados Relacional PostgreSQL

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos atendidos:

- **Java 21**: O projeto utiliza o Java 21. Certifique-se de que sua IDE está configurada com o Java 21.
- **Maven**: Usado para gerenciar dependências e construir o projeto.

## Passos para Configuração

1. **Clone o Repositório:**

   Abra o terminal e clone o repositório usando o comando:

   ```bash
   git clone https://github.com/bpcavalcante/fiap-gerenciamento-clientes

2. **Acesse a Branch main:**

   Depois de clonar o repositório, navegue até o diretório do projeto e altere para a branch main:

   ```bash
   git checkout main

3. **Importe o Projeto na IDE:**

- Abra sua IDE preferida (por exemplo, IntelliJ IDEA ou Eclipse).
- Certifique-se de que o **Java 21** está configurado como JDK.
- Importe o projeto como um projeto Maven existente. 

4. **Construir o Projeto:**

   No terminal, dentro do diretório do projeto, execute o comando Maven para construir o projeto, ou em algumas IDE já constroem automaticamente:

   ```bash
   mvn clean install

5. **Executar o Projeto:**
   Depois de construir o projeto, você pode executá-lo diretamente na IDE ou via linha de comando:
   ```bash
   mvn spring-boot:run

  O servidor será iniciado na porta **8082**


6. **Subir o container Docker:**
   Você precisará subir o container com as configurações, que estão no arquivo docker-compose dentro do projeto:
   ```bash
   docker-compose up

  O servidor PostgreSQL será iniciado na porta **5452**
  

7. **Testando o Sistema:**
   Use os comandos curl abaixo para testar as funcionalidades do sistema:
   - **Cadastrar Cliente**
     ```bash
      curl --location 'localhost:8082/clientes' \
      --header 'Content-Type: application/json' \
      --data-raw '{
          "nome":"Bruno",
          "email" : "bruno@gmail.com",
          "telefone": "11947527413",
          "endereco": "Rua Padre Jose Vieira de Matos 970"
      }'
   - **Atualizar Cliente**
     ```bash
      curl --location --request PUT 'localhost:8080/clientes/4' \
      --header 'Content-Type: application/json' \
      --data-raw '{
          "nome":"Bruno 254",
          "email" : "bruno@gmail.com",
          "telefone": "11947527413",
          "endereco": "Rua Padre Jose Vieira de Matos 970"
      }'
   - **Visualizar Cliente**
     ```bash
      curl --location 'localhost:8082/clientes/1'
   - **Excluir Cliente**
     ```bash
      curl --location --request DELETE 'localhost:8080/clientes/2'    
8. **Verificando doc Swagger:**
   Acesse o link http://localhost:8082/swagger-ui/index.html com o projeto rodando
