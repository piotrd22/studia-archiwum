#!/bin/bash

docker image inspect zad2-api | grep Architecture

docker image inspect mongo | grep Architecture
