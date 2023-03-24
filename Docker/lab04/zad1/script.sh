#!/bin/bash

docker volume create nginx_data

docker run -d \
  --name lab04zad1 \
  -p 80:80 \
  -v nginx_data:/usr/share/nginx/html \
  nginx
  
docker exec -it lab04zad1 sh -c "echo 'Hello World!' > /usr/share/nginx/html/index.html"

if [[ $(curl -s localhost) == *"Hello World!"* ]]; then
  echo "Nginx server is working correctly."
else
  echo "Nginx server is not working correctly."
fi
