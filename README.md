# Sistema de Matchmaking Multiplayer
Este projeto implementa um sistema de matchmaking multiplayer distribuído, utilizando uma arquitetura orientada a serviços com comunicação via RabbitMQ.

## 🧩 Serviços
### 🎮 Matchmaking Service (matchmaking-service)
Serviço responsável por receber solicitações de pareamento de jogadores, gerenciar estados das partidas (esperando, jogando, finalizado) e enviar eventos para outros serviços.
- Tecnologia: Java + Spring Boot
- Responsabilidades:
    - Parear jogadores automaticamente
    - Gerenciar estados via State Machine
    - Enviar eventos de criação/fim de partida via RabbitMQ
    - Armazenar dados em PostgreSQL
    - Cache de estados em Redis

### 📊 Stats Service (stats-service)
Serviço responsável por simular estatísticas de jogadores com base nos eventos recebidos.
- Tecnologia: Java + Spring Boot
- Responsabilidades:
    - Receber eventos de partidas finalizadas via RabbitMQ
    - Atualizar dados simulados no Redis
    - Expor API REST para retornar estatísticas

## 🔧 Tecnologias Utilizadas
- 🐇 RabbitMQ — Orquestração de eventos
- 🧠 Redis — Cache de estados e estatísticas
- 🐘 PostgreSQL — Persistência de dados
- 🐳 Docker — Containerização dos serviços