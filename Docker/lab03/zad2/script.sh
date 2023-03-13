#!/bin/bash

CONFIG_FILE=default.conf
PORT=80

docker build . -t lab03zad2 --build-arg CONFIG_FILE=$CONFIG_FILE

# docker run -p $PORT:80 -it lab03zad2
docker run -p $PORT:80 -d lab03zad2

# Test the Nginx server
if [[ $(curl -s localhost:$PORT) == *"Hello!"* ]]; then
  echo "Nginx server is working correctly."
else
  echo "Nginx server is not working correctly."
fi

docker stop $(docker ps -q --filter ancestor=lab03zad2)
docker rm $(docker ps -aq --filter ancestor=lab03zad2)
docker rmi lab03zad2