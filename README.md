# API REST - Delivery de Comida Saudável

API REST desenvolvida em Java com Spring Boot para gestão de pratos de um aplicativo de delivery de comida saudável, utilizando persistência em banco de dados PostgreSQL.

##  Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.4.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Bean Validation (@Valid)**
- **Terminal do IDLEJ** (para testes de requisição)
- **Porta utilizada 5432 do PosterSQL**
- 
## Arquitetura do Projeto
A aplicação foi desenvolvida seguindo a arquitetura em camadas:
- `Controller`: Recebe as requisições HTTP, valida as entradas com `@Valid` e retorna as respostas adequadas.
- `Service`: Contém as regras de negócio e faz as conversões entre Entidade e DTO (`toEntity` / `toDTO`).
- `Repository`: Interface que estende `JpaRepository` para comunicação direta com o PostgreSQL.
- `DTO (Records)`: Mapeamento de dados de entrada (`PratoRequestDTO`) e saída (`PratoResponseDTO`).
- `Model`: Entidade JPA (`Prato`) mapeada para a tabela `pratos`.

##  Endpoints Implementados

| Verbo | Rota | Descrição | Status HTTP |
|---|---|---|---|
| **POST** | `/pratos` | Cria um novo prato | `201 Created` |
| **GET** | `/pratos` | Lista todos os pratos | `200 OK` |
| **GET** | `/pratos/{id}` | Busca um prato por ID | `200 OK` ou `404 Not Found` |
| **GET** | `/pratos?categoria={cat}` | Filtra pratos por categoria | `200 OK` |
| **PUT** | `/pratos/{id}` | Atualiza um prato existente | `200 OK` ou `404 Not Found` |
| **DELETE** | `/pratos/{id}` | Remove um prato por ID | `204 No Content` ou `404 Not Found` |

##  Regra de Negócio Própria
No `PratoService`, foi implementada a seguinte validação antes do cadastro/atualização:
- **Verificação de duplicidade por nome:** Não é permitido cadastrar dois pratos com o mesmo nome para evitar duplicidades no cardápio.

##  Como Executar o Projeto

1. Certifique-se de ter o **PostgreSQL** rodando e crie o banco de dados `delivery_db`.
2. Configure as credenciais de acesso no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/delivery_db
   spring.datasource.username=postgres
   spring.datasource.password=123
   spring.jpa.hibernate.ddl-auto=update