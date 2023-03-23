#!/bin/bash

docker volume create nginx_data

docker run -d \
  --name lab04zad1 \
  -p 80:80 \
  -v nginx_data:/usr/share/nginx/html \
  nginx

echo "Hello World!" > \\\\wsl$\\docker-desktop-data\\data\\docker\\volumes\\nginx_data\\_data\\index.html

if [[ $(curl -s localhost) == *"Hello World!"* ]]; then
  echo "Nginx server is working correctly."
else
  echo "Nginx server is not working correctly."
fi

echo "Changes" > \\\\wsl$\\docker-desktop-data\\data\\docker\\volumes\\nginx_data\\_data\\index.html

if [[ $(curl -s localhost) == *"Changes"* ]]; then
  echo "Nginx server is working correctly."
else
  echo "Nginx server is not working correctly."
fi
