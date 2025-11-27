# API Receitas — README

## Introdução

Projeto simples em Java + Spring Boot para gerenciar **receitas**. Arquitetura leve inspirada em DDD, com camadas: `application` (controller), `domain` (entities, dtos, repositories, services) e `infrastructure` (configurações).

---

## Pré-requisitos

* Java 17+
* Maven
* Docker & Docker Compose (opcional, pra subir DB + app em containers)

---

## Como executar

### 1) Local (com Maven)

```bash
mvn clean spring-boot:run
```

A aplicação será iniciada na porta `8080` por padrão.

### 2) Com Docker Compose (Postgres + API)

Crie um `docker-compose.yml` com um serviço `postgres` e o serviço `api` apontando a variável `SPRING_DATASOURCE_URL` para o container do Postgres.

Em seguida:

```bash
docker-compose up --build -d
```

---

## Endpoints (introdução rápida)

Base: `/api/receitas`

| Método | Rota                          | Descrição                                                                                                                    |
| ------ | ----------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| POST   | `/api/receitas`               | Cria uma nova receita. Recebe `CriarReceitaRequestDto`. Retorna `CriarReceitaResponseDto` com o `id`.                        |
| GET    | `/api/receitas`               | Lista todas as receitas (retorna coleção de `Receita`).                                                                      |
| GET    | `/api/receitas/buscar/{nome}` | Lista receitas cujo nome contenha o texto informado (busca `Containing`).                                                    |
| PATCH  | `/api/receitas/{id}`          | Atualiza parcialmente a receita identificada pelo `id` (usa `CriarReceitaRequestDto` para campos que devem ser atualizados). |
| DELETE | `/api/receitas/{id}`          | Remove a receita pelo `id`.                                                                                                  |

> Observação: Não existe um endpoint `GET /api/receitas/{id}` no projeto — o único endpoint que usa `/{id}` é o `PATCH` e o `DELETE`.

---

## DTOs (nomes)

* `CriarReceitaRequestDto` — request para criação/atualização (ex.: `nome`, `descricao`, `ingredientes`).
* `CriarReceitaResponseDto` — response após criação (inclui `id` e campos da receita).

---


