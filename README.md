# 🚗 Desafio Técnico MagnumBank - APIs FIPE (API-1 & API-2)

Este projeto implementa uma arquitetura de **microsserviços distribuídos** utilizando **Quarkus**, com integração à API da FIPE, mensageria assíncrona, persistência em banco de dados e cache.

---

# 📌 Arquitetura do Sistema

O fluxo de dados ocorre da seguinte forma:

API-1 → Consulta FIPE (marcas)
→ Publica mensagem no RabbitMQ

RabbitMQ → Fila "marcas"

API-2 → Consome fila
→ Consulta FIPE (modelos)
→ Persiste dados no PostgreSQL
→ Cache no Redis

---

# ⚙️ Tecnologias Utilizadas

- ☕ Java 21
- ⚡ Quarkus
- 🐇 RabbitMQ (mensageria)
- 🐘 PostgreSQL (persistência)
- 🧠 Redis (cache)
- 🐳 Docker & Docker Compose
- 🌐 MicroProfile Rest Client
- 📩 MicroProfile Reactive Messaging
- 🔗 API FIPE (https://fipe.parallelum.com.br)

---

# 🚀 Como Executar o Projeto

## 1. Pré-requisitos

Certifique-se de ter instalado:

- Docker
- Docker Compose

---

## 2. Subir o ambiente completo

Na raiz do projeto, execute:

```bash
docker-compose up --build
```

Isso irá iniciar:

API-1: http://localhost:8080

API-2: http://localhost:8081

RabbitMQ UI: http://localhost:15672

PostgreSQL: localhost:5432

Redis: localhost:6379

---

# 🔐 Credenciais padrão
## PostgreSQL
Host: localhost

Database: test

User: postgres

Password: postgres

## RabbitMQ
User: guest

Password: guest

---

# 📡 Serviços
## 🔹 API-1

Responsável por:

Consultar marcas na API FIPE

Enviar dados para fila RabbitMQ

## Endpoint
```bash
GET /marcas/{tipo}
```

## Tipos suportados
cars

trucks

motorcycles

## Exemplo de uso
```bash
GET http://localhost:8080/marcas/cars
```


## 🔹 API-2

Responsável por:

Consumir mensagens da fila marcas

Consultar modelos na FIPE

Persistir dados no PostgreSQL

Processar informações de veículos

---
## 📬 Mensageria (RabbitMQ)

Fila utilizada:
```bash
marcas
```

Fluxo:
```bash
API-1 → envia MarcaDTO → RabbitMQ → API-2 consome
```

---

## 🗄️ Banco de Dados (PostgreSQL)

Banco utilizado:
```bash
teste
```

A API-2 salva informações como:

código da marca

Nome da marca

Modelos do veículo

Tipo de veículo

---

## 🧠 Cache (Redis)

Utilizado para otimizar consultas de veículos

Reduz chamadas repetidas à API FIPE

Host: redis://redis:6379

---

## 🧪 Como Testar o sistema

1. Buscar marcas (API-1)
```bash
GET http://localhost:8080/marcas/cars
```

2. Ver consumo da API-2
 ```bash
docker logs -f api-2
```

Exemplo de saída:

Recebido: Fiat | cars

Modelos da marca Fiat:

 - 001 | Uno
 - 002 | Toro


3. Acessar RabbitMQ
Acesse:
 ```bash
http://localhost:15672
```
Login:
user: guest

password: guest

4. Ver dados no PostgreSQL
Você pode usar:

DBeaver

PgAdmin

CLI psql

---

## 🌐 API Externa (FIPE)
 ```bash
https://fipe.parallelum.com.br/api/v2
```

Endpoints utilizados:
 ```bash
/{vehicleType}/brands
/{vehicleType}/brands/{brandId}/models
```

