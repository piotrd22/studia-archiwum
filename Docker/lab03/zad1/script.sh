#!/bin/bash

PORT=80

# docker-compose up lab03zad1 # -- with compose or just command with volume -- I added a compose file rather in the form of notes
docker build . -t lab03zad1
docker run -p $PORT:80 -v C:/Users/"Piotr Damrych"/Desktop/UCZELNIA/studia-archiwum/Docker/lab03/zad1/index.html:/usr/share/nginx/html/index.html:ro -d lab03zad1

status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://localhost:$PORT)
if [[ "$status_code" -ne 200 ]] ; then
  echo "Server is not running properly"
else
  echo "Server is running properly"
fi

docker stop $(docker ps -q --filter ancestor=lab03zad1)
docker rm $(docker ps -aq --filter ancestor=lab03zad1)
docker rmi lab03zad1