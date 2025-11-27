# 📘 Projeto API de Receitas (DDD + Spring Boot)

Este projeto é uma API simples de gerenciamento de **Receitas** desenvolvida em **Java 17** com **Spring Boot**, organizada utilizando princípios de **DDD (Domain-Driven Design)** e preparada para rodar em contêineres Docker.

A API permite:

* Criar receitas
* Listar receitas
* Buscar receita por ID
* Atualizar receitas (via PATCH)
* Excluir receitas

Toda a estrutura foi pensada para ser limpa, escalável e de fácil manutenção.

---

# 📂 Estrutura do Projeto

```
src
└── main
    ├── java
    │   └── com.exemplo.receitas
    │       ├── application
    │       │   ├── controllers
    │       │   │   └── ReceitaController.java
    │       │   ├── dtos
    │       │   │   ├── CriarReceitaRequestDto.java
    │       │   │   └── CriarReceitaResponseDto.java
    │       │   └── services
    │       │       └── ReceitaService.java
    │       ├── domain
    │       │   └── Receita.java
    │       ├── infrastructure
    │       │   ├── configurations
    │       │   │   └── CorsConfig.java
    │       │   └── repositories
    │       │       │   └── ReceitaRepository.java
    │       └── ReceitasApplication.java
    └── resources
        ├── application.properties
        └── data.sql (opcional para cargas iniciais)
```

---

# 📌 Domínios

## **🟦 Domain**

Contém a entidade principal da API:

### `Receita.java`

A entidade que representa uma receita no sistema. Contém os campos principais e suas validações.

---

# 📌 DTOs

Localizados em `application/dtos`.

### ✔ **CriarReceitaRequestDto.java**

Record usado para receber requisições de criação/atualização:

```java
public record CriarReceitaRequestDto(String nome, String descricao, Double valor) {}
```

### ✔ **CriarReceitaResponseDto.java**

Retorno padronizado das operações:

```java
public record CriarReceitaResponseDto(UUID id, String nome, String descricao, Double valor) {}
```

---

# 📌 Application Layer

## **Controllers** → `ReceitaController.java`

Expõe os endpoints da API.

Rotas:

* **POST /receitas** – cria uma receita
* **GET /receitas** – lista todas
* **GET /receitas/{id}** – busca por ID
* **PATCH /receitas/{id}** – atualiza parcialmente
* **DELETE /receitas/{id}** – exclui

## **Services** → `ReceitaService.java`

Onde fica a regra de negócio.

Exemplo de método PATCH:

```java
public void atualizarReceita(UUID id, CriarReceitaRequestDto dto) {
    Receita receitaExistente = receitaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

    receitaExistente.setNome(dto.nome());
    receitaExistente.setDescricao(dto.descricao());
    receitaExistente.setValor(dto.valor());

    receitaRepository.save(receitaExistente);
}
```

---

# 📌 Infraestrutura

## **CORS Config** → `CorsConfig.java`

Permite requisições de clientes externos.

## **Repository** → `ReceitaRepository.java`

Interface JPA:

```java
public interface ReceitaRepository extends JpaRepository<Receita, UUID> {}
```

---

# 🐳 Docker

Um ambiente simples com **Spring Boot + PostgreSQL**.

## Exemplo de `docker-compose.yml` para desenvolvimento:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: receitas-db
    environment:
      POSTGRES_DB: receitas
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    restart: always

  api:
    build: .
    container_name: receitas-api
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/receitas
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    restart: always

volumes:
  pgdata:
```

---

# ▶ Como Rodar o Projeto

### 1️⃣ Clonar o repositório

```
git clone https://github.com/seu-repo/projeto-receitas.git
cd projeto-receitas
```

### 2️⃣ Rodar com Docker

```
docker-compose up --build -d
```

### 3️⃣ Acessar a API

```
http://localhost:8080/receitas
```

---

# 📮 Exemplos de Requests

## Criar receita

```http
POST /receitas
Content-Type: application/json

{
  "nome": "Bolo de Cenoura",
  "descricao": "Cobertura de chocolate",
  "valor": 29.90
}
```

## Atualizar receita

```http
PATCH /receitas/UUID_AQUI
{
  "nome": "Bolo de Cenoura Gourmet"
}
```

---

# ✔ Conclusão

Este projeto demonstra:

* Arquitetura organizada baseada em DDD
* Uso de DTOs modernos com record
* Serviço limpo com regras encapsuladas
* Repositório JPA desacoplado
* Docker pronto para desenvolvimento

Se quiser, posso **adicionar badges**, **melhorar o visual**, **gerar diagrama**, ou **criar documentação da API com Swagger**.
