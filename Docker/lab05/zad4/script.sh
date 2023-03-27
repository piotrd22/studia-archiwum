#!/bin/bash

docker build . -t lab05zad4

docker run -it -p 5000:5000 -v C:/Users/Piotr Damrych/Desktop/UCZELNIA/studia-archiwum/Docker/lab05/zad4/myapp lab05zad4
