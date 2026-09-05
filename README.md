# 🎬 Catálogo de Filmes API

API REST para gerenciamento de um catálogo de filmes, diretores e listas de filmes.

O projeto foi desenvolvido com **Java e Spring Boot**, com foco na prática de desenvolvimento de APIs REST, persistência de dados, relacionamentos entre entidades, filtros, paginação e documentação com OpenAPI/Swagger.

## 🚀 Funcionalidades

### 🎥 Filmes

* Cadastro de filmes
* Consulta de filmes com paginação
* Filtros de filmes
* Consulta de filme por ID
* Atualização de filmes
* Exclusão de filmes
* Associação de filmes a diretores

### 🎬 Diretores

* Cadastro de diretores
* Consulta de diretores com paginação
* Filtros de diretores
* Consulta de diretor por ID
* Atualização de diretores
* Exclusão de diretores

### 📋 Listas

* Criação de listas de filmes
* Consulta de listas com paginação
* Busca de listas por título
* Consulta de lista por ID
* Atualização de listas
* Exclusão de listas
* Adição de filmes às listas
* Remoção de filmes das listas

## 🛠️ Tecnologias

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Flyway**
* **Bean Validation**
* **Lombok**
* **OpenAPI / Swagger**
* **Maven**

## 🏗️ Estrutura do projeto

O projeto é organizado por domínio, separando as responsabilidades de cada recurso da API.

```text
src/main/java/dev/joaorooliveira/catalogo_filmes
│
├── domain
│   ├── filme
│   │   ├── dto
│   │   ├── Filme.java
│   │   ├── FilmeController.java
│   │   ├── FilmeRepository.java
│   │   ├── FilmeService.java
│   │   └── ...
│   │
│   ├── diretor
│   │   ├── dto
│   │   ├── Diretor.java
│   │   ├── DiretorController.java
│   │   ├── DiretorRepository.java
│   │   ├── DiretorService.java
│   │   └── ...
│   │
│   └── lista
│       ├── dto
│       ├── Lista.java
│       ├── ListaController.java
│       ├── ListaRepository.java
│       ├── ListaService.java
│       └── ...
│
└── infra
    └── springdoc
        └── SpringDocConfiguration.java
```

## 🗄️ Modelo de dados

A aplicação possui três entidades principais:

```text
Diretor
   │
   │ 1:N
   ▼
Filme
   ▲
   │ N:N
   │
Lista
```

### Relacionamentos

* Um **diretor** pode estar associado a vários filmes.
* Um **filme** pode estar associado a um diretor.
* Uma **lista** pode possuir vários filmes.
* Um **filme** pode pertencer a várias listas.

O relacionamento entre `Lista` e `Filme` é realizado através de uma tabela intermediária:

```text
lista_filme
├── lista_id
└── filme_id
```

Essa tabela é responsável por armazenar as associações entre as listas e os filmes.

## 🔎 Filtros e paginação

A API possui consultas paginadas e filtros para facilitar a busca dos registros.

Exemplo de consulta de filmes:

```http
GET /filmes?nome=matrix&avaliacao=5&page=0&size=10
```

Exemplo de consulta de diretores:

```http
GET /diretores?nome=nolan&page=0&size=10
```

Exemplo de busca de listas pelo título:

```http
GET /listas?titulo=terror&page=0&size=10
```

## 📚 Documentação da API

A API utiliza **OpenAPI/Swagger** para documentação dos endpoints.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação permite visualizar os endpoints, parâmetros, requisições e respostas disponíveis na API.

## 📌 Endpoints

### 🎥 Filmes

| Método   | Endpoint       | Descrição              |
| -------- | -------------- | ---------------------- |
| `POST`   | `/filmes`      | Cadastra um filme      |
| `GET`    | `/filmes`      | Busca filmes           |
| `GET`    | `/filmes/{id}` | Busca um filme pelo ID |
| `PUT`    | `/filmes/{id}` | Atualiza um filme      |
| `DELETE` | `/filmes/{id}` | Remove um filme        |

### 🎬 Diretores

| Método   | Endpoint          | Descrição                |
| -------- | ----------------- | ------------------------ |
| `POST`   | `/diretores`      | Cadastra um diretor      |
| `GET`    | `/diretores`      | Busca diretores          |
| `GET`    | `/diretores/{id}` | Busca um diretor pelo ID |
| `PUT`    | `/diretores/{id}` | Atualiza um diretor      |
| `DELETE` | `/diretores/{id}` | Remove um diretor        |

### 📋 Listas

| Método   | Endpoint              | Descrição               |
| -------- | --------------------- | ----------------------- |
| `POST`   | `/listas`             | Cria uma lista          |
| `GET`    | `/listas`             | Busca listas            |
| `GET`    | `/listas/{id}`        | Busca uma lista pelo ID |
| `PUT`    | `/listas/{id}`        | Atualiza uma lista      |
| `DELETE` | `/listas/{id}`        | Remove uma lista        |
| `POST`   | `/listas/{id}/filmes` | Adiciona filmes à lista |
| `DELETE` | `/listas/{id}/filmes` | Remove filmes da lista  |

## ▶️ Como executar

### Pré-requisitos

* Java 21
* PostgreSQL
* Git

### 1. Clone o repositório

```bash
git clone https://github.com/Joaorooliveira/catalogo-filmes
cd catalogo-filmes
```

### 2. Configure o PostgreSQL

Crie um banco de dados chamado:

```text
catalogo_filmes
```

Depois configure as credenciais do banco no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_filmes
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

### 3. Execute a aplicação

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

### 4. Acesse o Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido com fins educacionais para praticar o desenvolvimento de **APIs REST utilizando Java e Spring Boot**.

Durante o desenvolvimento foram aplicados conceitos como:

* Arquitetura em camadas
* Organização por domínio
* DTOs
* JPA/Hibernate
* Relacionamentos `@ManyToOne` e `@ManyToMany`
* Spring Data JPA
* Specifications
* Paginação
* Bean Validation
* Tratamento de exceções
* Migrações de banco com Flyway
* Documentação de APIs com OpenAPI/Swagger

## 👨‍💻 Autor

**João Oliveira**

GitHub: **Joaorooliveira**
