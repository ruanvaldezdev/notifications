🚀 Notification System Microservices
Sistema de notificações multi-canal (Email e SMS) desenvolvido com Spring Boot, RabbitMQ e arquitetura de microserviços.
🛠️ Tecnologias Principais

    Java 17 / Spring Boot
    RabbitMQ (Mensageria e Prioridade)
    PostgreSQL (Persistência)
    Eureka Server (Service Discovery)
    Spring Cloud Gateway (API Gateway)
    Twilio (Provedor SMS)
    MailerSender (Provedor Email)

🚦 Como Iniciar
1. Configuração de Variáveis de Ambiente
Crie um arquivo chamado .env na raiz do projeto e preencha com suas credenciais:
env

# .env na raiz do projeto
    TWILIO_SID=seu_sid_aqui
    TWILIO_TOKEN=seu_token_aqui
    API_TOKEN_EMAIL=seu_token_mailersender
    API_DOMAIN=seu_dominio_configurado

Use code with caution.
### 2. Subir os Containers
Certifique-se de que o Docker está rodando e execute:

    docker-compose up -d --build

    Ou docker compose up -d --build dependendo da sua versão.

📡 Endpoints e Fluxo de Uso
A porta principal de entrada via Gateway é: http://localhost:8082/api-service/api
### 1. Autenticação (Acesso Aberto)
A API utiliza JWT (Bearer Auth). Primeiro, registre-se e obtenha seu token.

    Registrar: POST /auth/register
        Payload: {"username": "seu_user", "password": "sua_senha"}

    Login: POST /auth/login
        Payload: {"username": "seu_user", "password": "sua_senha"}
        Importante: Copie o token recebido na resposta.

### 3. Enviar Notificação (POST /notifications)
O sistema valida o destinatário dinamicamente com base no canal escolhido.
Exemplo EMAIL:
json

    {
    "channel": "EMAIL",
    "recipient": "usuario@email.com",
    "message": "Sua fatura chegou!",
    "priority": "HIGH"
    }


### Exemplo SMS:
json

    {
    "channel": "SMS",
    "recipient": "+5511999999999",
    "message": "Seu código de verificação é 1234",
    "priority": "MEDIUM"
    }

## 4. Consultar Notificações (GET /notifications)
Retorna o histórico de notificações enviadas pelo usuário autenticado.
📖 Documentação Swagger
    Para visualizar e testar os endpoints interativamente, acesse:
    🔗 http://localhost:8083/api/swagger-ui/index.html
🏗️ Resumo da Infraestrutura

| Serviço | Porta | Descrição |
| :--- | :--- | :--- |
| **Gateway** | 8082 | Porta de entrada unificada |
| **API** | 8083 | Processamento e validação |
| **Eureka** | 8081 | Service Discovery |
| **Email** | 8084 | Consumer (MailerSender) |
| **SMS** | 8085 | Consumer (Twilio) |
| **RabbitMQ** | 15672 | Painel de controle (UI) |
