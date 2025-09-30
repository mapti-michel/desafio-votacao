# Desafio de Votação – Backend Spring Boot

## Descrição

Projeto para gerenciar votações de pautas em um sistema cooperativo.  
Cada associado possui um voto e as decisões são tomadas em assembleias por votação.

A aplicação expõe **API REST** pronta para integração com dispositivos móveis.

---

## Funcionalidades

- Criar pautas
- Abrir sessão de votação em uma pauta (tempo configurável, default 1 min)
- Registrar votos (SIM/NAO) de associados únicos por pauta
- Contabilizar votos e retornar resultado da sessão
- Facade fake de CPF (Tarefa Bônus 1)
- API versionada: `/api/v1/`

---

## Tecnologias

- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database (em memória)
- Maven
- Lombok

---

## Estrutura do Projeto
```
desafio-votacao/
│
├─ src/
│   ├─ main/
│   │   ├─ java/
│   │   │   └─ com/desafio/votacao/
│   │   │       ├─ controller/       # Endpoints REST
│   │   │       │   ├─ PautaController.java
│   │   │       │   ├─ SessaoController.java
│   │   │       │   └─ VotoController.java
│   │   │       │
│   │   │       ├─ dto/              # Request REST
│   │   │       │   ├─ SessaoRequest.java
│   │   │       │   └─ VotoRequest.java
│   │   │       ├─ entity/           # Models/Entities
│   │   │       │   ├─ Pauta.java
│   │   │       │   ├─ Sessao.java
│   │   │       │   ├─ Voto.java
│   │   │       │   └─ OpcaoVoto.java
│   │   │       │
│   │   │       ├─ repository/       # JpaRepositories
│   │   │       │   ├─ PautaRepository.java
│   │   │       │   ├─ SessaoRepository.java
│   │   │       │   └─ VotoRepository.java
│   │   │       │
│   │   │       └─ service/          # Lógica de negócio + CPF fake
│   │   │           ├─ PautaService.java
│   │   │           ├─ SessaoService.java
│   │   │           ├─ VotoService.java
│   │   │           └─ CpfFacadeFake.java
│   │   │
│   │   └─ resources/
│   │       ├─ application.properties
│   │       └─ logback-spring.xml (opcional, logs)
│   │
│   └─ test/
│       └─ java/com/desafio/votacao/
│           └─ DesafioVotacaoApplicationTests.java
│           └─ service/
│               └─ PautaServiceTest.java
│               └─ SessaoServiceTest.java
│               └─ VotoServiceTest.java
│
├─ postman/
│   └─ DesafioVotacao.postman_collection.json
│
├─ pom.xml
├─ README.md
└─ .gitignore
```

---

## Configuração do Banco (H2)

`application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

Console H2: http://localhost:8080/h2-console
User: sa
Password: (vazio)
```
# Compilar e instalar dependências
mvn clean install

# Rodar aplicação
mvn spring-boot:run

Porta padrão: 8080

URL base API: http://localhost:8080/api/v1

⚠️ Possíveis erros HTTP:

    - 404 CPF inválido
    - 403 UNABLE_TO_VOTE
    - 400 Sessão fechada ou associado já votou

# Observações

Facade CPF fake implementada para Tarefa Bônus 1
Sessão abre por default 1 minuto
Contabilização de votos pronta (SIM/NAO)
API versionada /api/v1/
Logs via Spring Boot

# Testes
Pode ser testado via Postman, Insomnia ou curl
Sequência sugerida:
Criar Pauta
Abrir Sessão
Registrar votos com CPFs aleatórios
Consultar resultado

# Testes Unitários
- Sequência sugerida:
- Pauta
- Abrir Sessão
- Registrar votos com CPFs aleatórios
- Consultar resultado

