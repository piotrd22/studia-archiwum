#!/bin/bash

PORT=80

docker-compose up lab03zad1 # -- with our compose or just command with volume
# docker build . -t lab03zad1
# docker run -p $PORT:80 -v C:\Users\Piotr Damrych\Desktop\UCZELNIA\studia-archiwum\Docker\lab03\zad1\my_index.html_folder:/usr/share/nginx/html:ro -d lab03zad1

docker stop $(docker ps -q --filter ancestor=lab03zad1)
docker rm $(docker ps -aq --filter ancestor=lab03zad1)
docker rmi lab03zad1