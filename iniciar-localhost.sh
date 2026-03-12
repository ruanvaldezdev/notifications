#!/bin/bash

# Cores para o terminal
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${GREEN}🚀 Iniciando o ecossistema de microserviços...${NC}"

# 1. SERVER (Geralmente o Config/Discovery - precisa de mais tempo)
echo -e "${YELLOW}Iniciando Server...${NC}"
(cd server && mvn spring-boot:run > ../server.log 2>&1) &
sleep 15 # Espera o server estabilizar

# 3. API
echo -e "${YELLOW}Iniciando API...${NC}"
(cd api && mvn spring-boot:run > ../api.log 2>&1) &
sleep 5

# 4. EMAIL
echo -e "${YELLOW}Iniciando Email...${NC}"
(cd email && mvn spring-boot:run > ../email.log 2>&1) &
sleep 5

# 5. GATEWAY (Geralmente por último para rotear serviços prontos)
echo -e "${YELLOW}Iniciando Gateway...${NC}"
(cd gateway && mvn spring-boot:run > ../gateway.log 2>&1) &

echo -e "${GREEN}✅ Todos os serviços foram disparados em segundo plano!${NC}"
echo "--------------------------------------------------------"
echo "Para acompanhar o log de algum serviço em tempo real:"
echo "Exemplo: tail -f api.log"
echo "--------------------------------------------------------"
echo "Para parar todos os serviços de uma vez: killall java"

# Mantém o script aguardando os processos
wait
