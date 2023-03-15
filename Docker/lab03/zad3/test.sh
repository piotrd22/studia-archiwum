#!/bin/bash

#docker-compose up

response=$(curl -s -H "Content-Type: application/json" http://localhost)

if [[ "$response" == "\"Hello World\"" ]]; then
  echo "Test passed"
else
  echo "Test failed: response was $response"
fi
