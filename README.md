---

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

O **Product API** é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar produtos e categorias. A API oferece operações CRUD (Create, Read, Update, Delete) e suporta paginação, caching com Redis e documentação automática com Swagger.

## Índice

1. [Visão Geral](#visão-geral)
2. [Funcionalidades](#funcionalidades)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Pré-requisitos](#pré-requisitos)
5. [Configuração e Execução](#configuração-e-execução)
6. [Endpoints da API](#endpoints-da-api)
7. [Documentação da API](#documentação-da-api)
8. [Caching com Redis](#caching-com-redis)
9. [Licença](#licença)

---

## Visão Geral

O **Product API** foi projetado para ser uma solução simples e eficiente para gerenciar produtos e categorias. Ele utiliza DTOs, mapeamento automático com MapStruct, caching com Redis e documentação com Swagger/OpenAPI.

---

## Funcionalidades

- **CRUD Completo**: Criação, leitura, atualização e exclusão de produtos e categorias.
- **Paginação**: Suporte a paginação para listar produtos.
- **Caching**: Armazenamento de dados frequentemente acessados no Redis para melhorar o desempenho.
- **Documentação Automática**: Documentação interativa da API com Swagger.
- **Validação de Dados**: Validação de entradas para garantir consistência dos dados.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JDBC**
- **Spring Cache**
- **Redis**
- **PostgreSQL** (ou outro banco de dados relacional)
- **MapStruct** (para mapeamento entre DTOs e entidades)
- **Swagger/OpenAPI** (documentação da API)
- **Gradle** (gerenciamento de dependências)

---

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter os seguintes requisitos instalados:

1. **JDK 17**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
2. **PostgreSQL**: [Download PostgreSQL](https://www.postgresql.org/download/)
3. **Redis**: [Download Redis](https://redis.io/download)
4. **Gradle ou Maven**: [Gradle](https://gradle.org/install/) | [Maven](https://maven.apache.org/install.html)
5. **IDE (opcional)**: IntelliJ IDEA, Eclipse ou VS Code.

---

## Configuração e Execução

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/product-api.git
cd product-api
```

### 2. Configurar o Banco de Dados

Edite o arquivo `application.properties` na pasta `src/main/resources` para configurar as credenciais do banco de dados PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/productdb
spring.datasource.username=postgres
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configurar o Redis

Certifique-se de que o Redis esteja instalado e em execução. Configure as propriedades do Redis no arquivo `application.properties`:

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.cache.type=redis
spring.cache.redis.time-to-live=3600s
```

### 4. Executar o Projeto

#### Usando Gradle:
```bash
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints da API

| Método HTTP | Endpoint               | Descrição                                   |
|-------------|------------------------|---------------------------------------------|
| `POST`      | `/products`            | Cria um novo produto.                       |
| `GET`       | `/products/{id}`       | Busca um produto pelo ID.                   |
| `GET`       | `/products`            | Lista todos os produtos com paginação.      |
| `DELETE`    | `/products/{id}`       | Exclui um produto pelo ID.                  |

Para mais detalhes, consulte a [documentação da API](#documentação-da-api).

---

## Documentação da API

A API possui documentação interativa usando Swagger. Após iniciar a aplicação, acesse:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

---

## Caching com Redis

O projeto utiliza o Redis para caching de dados frequentemente acessados. As configurações de cache estão definidas no arquivo `application.properties`:

```properties
spring.cache.type=redis
spring.cache.redis.time-to-live=3600s
```

O cache é usado nos métodos `findById` e `findAll` para melhorar o desempenho.

---

## Contribuição

Contribuições são bem-vindas! Se você encontrar bugs ou quiser adicionar novas funcionalidades, abra uma issue ou envie um pull request.

---

