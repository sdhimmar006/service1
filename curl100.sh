#!/bin/bash

for i in $(eval echo {1..100})
do
  curl http://localhost:8081/service1
done