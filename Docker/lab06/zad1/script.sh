#!/bin/bash

docker network create --driver bridge --subnet 192.168.1.1/24 --gateway 192.168.1.1 my_bridge

docker run -it --name bridge_test --network my_bridge alpine sh 

ping 192.168.1.1
