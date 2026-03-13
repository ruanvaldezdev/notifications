
# 🚀 Notifications

**Capacitando comunicação eficiente com notificações em tempo real!**

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Microservices-green)
![Docker](https://img.shields.io/badge/Docker-Containers-blue)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-MessageQueue-orange)

---

# 📚 Sumário

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Primeiros Passos](#primeiros-passos)
  - [Pré-requisitos](#pré-requisitos)
  - [Instalação](#instalação)
  - [Uso](#uso)
  - [Testes](#testes)
- [Roadmap](#roadmap)
- [Contribuindo](#contribuindo)
- [Licença](#licença)
- [Agradecimentos](#agradecimentos)

---

# 📖 Visão Geral

O **Notifications** é um sistema baseado em **arquitetura de microsserviços** responsável por gerenciar e enviar notificações através de múltiplos canais como:

- 📧 Email
- 📱 SMS
- 🔔 Push notifications

O sistema utiliza **mensageria assíncrona** para garantir alta escalabilidade e confiabilidade no envio das notificações.

Esse projeto foi projetado para aplicações modernas que precisam de **entrega eficiente de mensagens e comunicação entre serviços distribuídos**.

---

# ⚙️ Funcionalidades

## Arquitetura de Microsserviços

- Registro e descoberta de serviços usando **Eureka Server**
Client
│
▼
API Gateway
│
▼
API Service
│
▼
RabbitMQ
│
├── Email Service
├── SMS Service
└── Notification Processing
- Comunicação entre serviços via **RabbitMQ**
- Gateway centralizado para roteamento de requisições
- Documentação da API com **Swagger**

---

## Qualidade de Código

- Gerenciamento de dependências com **Maven**
- Estrutura seguindo boas práticas do **Spring Boot**
- Testes unitários para componentes críticos

---

## Integrações

O sistema integra serviços externos para envio de notificações:

- 📩 **Twilio** para envio de SMS
- 📧 **MailerSend** para envio de emails
- 🐇 **RabbitMQ** para processamento de mensagens

---

## Segurança

- Autenticação utilizando **JWT**
- Criptografia de senhas com **BCrypt**
- Validações personalizadas para integridade dos dados

---

# 🏗 Arquitetura

<img width="1600" height="721" alt="image" src="https://github.com/user-attachments/assets/c8390b92-975f-4e8c-a0c4-3658d447e1ba" />


---

# 📁 Estrutura do Projeto

```

notifications/
├── api
│   ├── src
│   ├── Dockerfile
│   └── pom.xml
│
├── email
│   ├── src
│   ├── Dockerfile
│   └── pom.xml
│
├── sms
│   ├── src
│   ├── Dockerfile
│   └── pom.xml
│
├── gateway
│   ├── src
│   ├── Dockerfile
│   └── pom.xml
│
├── server
│   ├── src
│   ├── Dockerfile
│   └── pom.xml
│
└── docker-compose.yaml

````

Cada serviço possui responsabilidade isolada, garantindo **baixo acoplamento e alta escalabilidade**.

---

# 🧰 Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Cloud**
- **RabbitMQ**
- **Docker**
- **PostgreSQL**
- **Maven**
- **JWT**
- **Swagger**

---

# 🚀 Primeiros Passos

## Pré-requisitos

Antes de executar o projeto, instale:

- Java 17+
- Docker
- Maven

---

# 📦 Instalação

Clone o repositório:

```bash
git clone https://github.com/donruan3001/notifications
````

Entre na pasta do projeto:

```bash
cd notifications
```

Instale as dependências:

```bash
mvn install
```

---

# ▶️ Uso

Executando com Docker:

```bash
docker-compose up --build
```

Após iniciar os containers, os serviços estarão disponíveis no ambiente local.

---

# 🧪 Testes

Execute os testes com:

```bash
mvn test
```

---

# 🗺 Roadmap

Próximas melhorias planejadas:

* [ ] Sistema de prioridade de notificações
* [ ] Retry automático de mensagens
* [ ] Dead Letter Queue
* [ ] Dashboard de monitoramento
* [ ] Sistema de notificações push

---

# 🤝 Contribuindo

Contribuições são bem-vindas!

1. Fork o projeto
2. Crie uma branch para sua feature

```
git checkout -b feature/minha-feature
```

3. Commit suas alterações

```
git commit -m "feat: nova funcionalidade"
```

4. Envie para o repositório

```
git push origin feature/minha-feature
```

5. Abra um Pull Request

---

# 📜 Licença

Este projeto está sob a licença definida no arquivo **LICENSE**.

---

# 🙌 Agradecimentos

* Comunidade **Spring Boot**
* Documentação do **RabbitMQ**
* Ferramentas open source utilizadas no projeto

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório.

```
```
