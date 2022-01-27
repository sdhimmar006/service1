#!/bin/bash

for i in $(eval echo {1..$1})
do
  curl $2
done