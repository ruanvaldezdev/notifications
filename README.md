PARA INICIAR O CONTAINER É NECESSARIO APENAS SUBIR OS CONTAINERS

DOCKER-COMPOSE UP -D OU DOCKER COMPOSE UP -D

TER O JDK 17 INSTALADO PARA REALIZAR O RUN DAS APLICAÇÕES

 E sudo chmod +x iniciar-localhost.sh
 sudo ./iniciar-localhost.sh

porém deve realizar os registros nos determinados microservices

para sms foi ultilizado o twillo  e para email foi ulizado o mailersender

crie um .env para inserir suas variaveis de ambiente das duas apis desta maneira

# .env na raiz do projeto
TWILIO_SID=
TWILIO_TOKEN=
API_TOKEN_EMAIL=
API_DOMAIN= 


para realizar a requisições pode ultizar do postman 
    em localhost:8082/api

    os endpoints são /auth/login
    auth/register

    notification POST
    notification GET 

    
    OU ULTILIZAR O SWAGGER QUE ESTARÁ NA http://localhost:8083/api/swagger-ui/index.html



    