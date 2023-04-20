#!/bin/bash

# we dont need to use nginx.conf bc default is good

# docker compose up

docker create app_network

docker build -t app --build-arg GIT_REPO https://github.com/szymon-kalkowski/meme-generator.git .

docker run -d --network app_network -p 80:80 --name app app