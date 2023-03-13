#!/bin/bash

PORT=80

# docker-compose up lab03zad1 # -- with our compose or just command with volume
docker build . -t lab03zad1
docker run -p $PORT:80 -v C:/Users/"Piotr Damrych"/Desktop/UCZELNIA/studia-archiwum/Docker/lab03/zad1/index.html:/usr/share/nginx/html/index.html:ro -d lab03zad1

# Test the Nginx server
if [[ $(curl -s localhost:$PORT) == *"Hello World!"* ]]; then
  echo "Nginx server is working correctly."
else
  echo "Nginx server is not working correctly."
fi

docker stop $(docker ps -q --filter ancestor=lab03zad1)
docker rm $(docker ps -aq --filter ancestor=lab03zad1)
docker rmi lab03zad1