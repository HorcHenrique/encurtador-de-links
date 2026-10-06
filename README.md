# Encurtador de links

API REST que recebe uma URL, devolve um link curto e redireciona quem acessa esse link para o endereço original. Cada link pertence a um dono e guarda quantas vezes foi acessado.

Feito em Java 21 com Spring Boot, para praticar API REST, JPA e organização em camadas (controller, service, repository).

## O que faz

- Cria um código curto de 10 caracteres para uma URL.
- Se a mesma URL já foi encurtada, devolve o link que já existe em vez de criar outro.
- Redireciona (HTTP 302) do link curto para a URL original.
- Conta os acessos e guarda a data do último acesso de cada link.
- Lista todos os links de um dono.
- Valida a entrada: URL malformada ou `ownerId` ausente devolve 400.
- Link inexistente, ou acessado com o dono errado, devolve 404.

## Tecnologias

- Java 21
- Spring Boot 4 (Web, Data JPA, Validation, Security, Actuator)
- PostgreSQL, ou H2 em memória para rodar sem configurar nada
- springdoc-openapi (Swagger UI)
- Maven

## Como rodar

Precisa só do Java 21 instalado.

```bash
git clone https://github.com/HorcHenrique/encurtador-de-links.git
cd encurtador-de-links
./mvnw spring-boot:run
```

A API sobe em `http://localhost:9090` usando um banco H2 em memória. Os dados somem quando a aplicação para.

A documentação interativa fica em `http://localhost:9090/swagger-ui/index.html`.

### Usando PostgreSQL

Defina as variáveis de ambiente antes de rodar:

```bash
export DATABASE_URL=jdbc:postgresql://host:5432/banco
export DATABASE_USERNAME=usuario
export DATABASE_PASSWORD=senha
./mvnw spring-boot:run
```

`APP_BASE_URL` muda o endereço usado para montar os links curtos (o padrão é `http://localhost:9090`).

## Endpoints

| Método | Rota | O que faz |
|---|---|---|
| POST | `/create` | Cria um link curto |
| GET | `/shrtLnk/{ownerId}/{codigo}` | Redireciona para a URL original |
| GET | `/get/{ownerId}` | Lista os links de um dono |
| GET | `/health/db` | Diz se o banco está respondendo |

### Exemplo

Criar um link:

```bash
curl -X POST http://localhost:9090/create \
  -H "Content-Type: application/json" \
  -d '{"ownerId": 1, "url": "https://github.com/HorcHenrique"}'
```

```json
{"code":"uiZAeXsnDT","shortUrl":"http://localhost:9090/shrtLnk/1/uiZAeXsnDT"}
```

Abrir `http://localhost:9090/shrtLnk/1/uiZAeXsnDT` no navegador leva para a URL original.

Listar os links do dono 1:

```bash
curl http://localhost:9090/get/1
```

```json
[{"originalUrl":"https://github.com/HorcHenrique","OwnerId":1,"ShortUrl":"http://localhost:9090/shrtLnk/1/uiZAeXsnDT"}]
```

## Estrutura

```
src/main/java/com/example/demo
├── Controller   rotas HTTP
├── Service      regras de negócio
├── Repository   acesso ao banco (Spring Data JPA)
├── Entity       tabelas (Link, ClickEvent)
├── dto          formatos de entrada e saída
└── config       configuração de segurança
```

## O que falta

- Autenticação. Hoje qualquer pessoa cria links para qualquer `ownerId`.
- Registrar cada clique na tabela `click_events`. A tabela existe, mas por enquanto só o contador do link é atualizado.
- Data de expiração e apelido personalizado para o link.
- Testes das regras de negócio.
