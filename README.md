# Kanban API

API REST para gerenciamento de tarefas no estilo Kanban, desenvolvida com Spring Boot e Java 21.

## Tecnologias
- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- GitHub Actions (CI/CD)

## Endpoints

| Método | Endpoint        | Descrição              |
|--------|-----------------|------------------------|
| GET    | /api/tasks      | Lista todas as tarefas |
| GET    | /api/tasks/{id} | Busca tarefa por ID    |
| POST   | /api/tasks      | Cria nova tarefa       |
| PUT    | /api/tasks/{id} | Atualiza uma tarefa    |
| DELETE | /api/tasks/{id} | Remove uma tarefa      |

## Status das Tarefas
- `A_FAZER`
- `EM_PROGRESSO`
- `CONCLUIDO`

## Como executar localmente
```bash
mvn spring-boot:run
```

## Como rodar os testes
```bash
mvn clean package
```

## CI/CD
Este projeto possui uma pipeline de integração contínua configurada via GitHub Actions.
A cada push na branch `main`, o robô automaticamente:
1. Baixa o código
2. Configura o Java 21
3. Compila e executa todos os testes com Maven
