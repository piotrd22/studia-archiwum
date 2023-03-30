#!/bin/bash

docker build . -t lab05zad5

docker run -it -p 80:80 lab05zad5
